package com.gray.lightcycles.server.net;

import com.gray.lightcycles.logic.net.Network;
import com.gray.lightcycles.server.main.Main;

import java.io.IOException;

public class Server
{
	public static final int IDLE = 0;
	public static final int WAITING = 1;
	public static final int PLAYING = 2;
	public static final int GAME_OVER = 3;

	public static final String BROADCAST_IP = "230.1.1.1";
	public static final int BROADCAST_PORT = 25535;

	public static String serverIP;
	public static int serverPort;

	public static Network network;
	public static int status;

	public static String winner;
	public static int score;


	public Server()
	{
		network = new Network(BROADCAST_IP, BROADCAST_PORT);
		status = IDLE;
		try
		{
			serverIP = network.getIP();
			serverPort = network.getPort();
		}
		catch(IOException e)
		{
			Main.logger.severe("Unable to retreive server details");
		}

	}

	public void run()
	{
		(new Thread(new ReceiveThread())).start();
		(new Thread(new BroadcastThread())).start();

		while(status == IDLE)
		{
			if(Main.game.getPlayers().size() >= Main.MIN_PLAYERS)
			{
				int players = Main.game.getPlayers().size();

				try
				{
					Thread.sleep(10000);
				}catch(Exception e)
				{
					e.printStackTrace();
				}

				if(Main.game.getPlayers().size() == players)
				{
					status = PLAYING;
				}
			}
			else
			{
				try
				{
					Thread.sleep(1000);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}


		(new Thread(new LogicThread())).start();

	}

	public void setState()
	{

	}



}
