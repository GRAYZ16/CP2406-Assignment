package com.gray.net;

import com.gray.main.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

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
				switch(command[2])
				{
					case "TURN":
						break;
					case "GO":

						break;
					case "JETWALL":

						break;
				}
				break;
			case "ADD":
				if(Server.status == Server.IDLE || Server.status == Server.WAITING)
				{
					//TODO: Add Player
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
					//TODO: Remove Player
					sendResponse("OKAY");
				}
				else if(Server.status == Server.PLAYING)
				{
					//TODO: Kill Player
					sendResponse("OKAY");
				}
				else
				{
					sendResponse("FAILED SERVER_ERROR");
				}
				break;
			case "GRID":

				break;
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
