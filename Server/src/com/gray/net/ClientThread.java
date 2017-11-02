package com.gray.net;

import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.main.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Random;

public class ClientThread implements Runnable
{
	private String message;
	private String senderIP;
	private int senderPort;

	public ClientThread(DatagramPacket packet)
	{
		this.message = new String(packet.getData(), packet.getOffset(), packet.getLength());
		this.senderIP = packet.getAddress().getHostAddress();
		this.senderPort = packet.getPort();
	}

	@Override
	public void run()
	{
		try
		{
			processCommand();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public void processCommand() throws IOException
	{
		String[] command = message.split(" ");
		Main.logger.info("Message Received");

		switch(command[0])
		{
			case "USER":
				Player player = Main.game.getPlayer(command[1]);

				switch(command[2])
				{
					case "TURN":
						if(command[3].equals("RIGHT"))
							player.turn(Player.RIGHT);
						else if(command[3].equals("LEFT"))
							player.turn(Player.LEFT);
						break;
					case "GO":
						if(command[3].equals("FASTER"))
							player.setVel(player.getVel().add(new Vector2d(0.3, 0.3)));
						else if(command[3].equals("SLOWER"))
							player.setVel(player.getVel().add(new Vector2d(-0.3, -0.3)));
						break;
					case "JETWALL":
						if(command[3].equals("ON"))
						{
							player.setLightWall(true);
						}
						else if(command[3].equals("OFF"))
						{
							player.setLightWall(false);
						}
						break;
				}

				Main.game.setPlayer(command[1], player);
				break;
			case "ADD":
				if(Server.status == Server.IDLE || Server.status == Server.WAITING)
				{
					Random random = new Random();
					Main.game.addPlayer(random.nextInt(Main.gridSize - 10) ,random.nextInt(Main.gridSize -10), command[1]);
					sendResponse("OKAY");
				}
				else if(Server.status == Server.PLAYING)
				{
					sendResponse("FAILED INGAME");
				}
				else if(Server.status == Server.GAME_OVER)
				{
					sendResponse("FAILED GAMEOVER");
				}
				else
				{
					sendResponse("FAILED ERROR");
				}
				break;
			case "REMOVE":
				if(Server.status == Server.IDLE || Server.status == Server.WAITING || Server.status == Server.GAME_OVER)
				{
					Main.game.removePlayer(command[1]);
					sendResponse("OKAY");
				}
				else if(Server.status == Server.PLAYING)
				{
					Main.game.removePlayer(command[1]);
					sendResponse("OKAY");
				}
				else
				{
					sendResponse("FAILED SERVER_ERROR");
				}
				break;
			case "GRID":
				sendResponse(Main.gridSize + "," + Main.gridSize);
				break;

			case "GAME":
				switch(Server.status)
				{
					case Server.IDLE:
						sendResponse("IDLE");
						break;
					case Server.WAITING:
						sendResponse("WAITING FOR USERS");
						break;
					case Server.PLAYING:
						sendResponse("PLAYING");
						break;
					case Server.GAME_OVER:
						sendResponse("GAME OVER");//TODO: Add Winner
						break;
				}
			case "SAVE":
				break;
			default:
				Main.logger.info("Incorrect Message Received: " + message);
		}
	}

	private void sendResponse(String message) throws IOException
	{
		Server.network.sendPacket(senderIP, senderPort, message);
	}
}
