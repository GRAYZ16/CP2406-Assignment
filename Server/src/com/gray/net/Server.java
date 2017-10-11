package com.gray.net;

import com.gray.lightcycleslogic.net.Network;
import com.gray.main.Main;

import java.io.IOException;
import java.net.DatagramPacket;

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

		run();
	}

	private void run()
	{
		(new Thread(new BroadcastThread())).start();

		int counter = 0;

		while(true)
		{
			Main.logger.info("WAITING");
			DatagramPacket packet = network.receivePacket();
			(new Thread(new ClientThread(packet))).start();

			counter++;
			Main.game.setPlayerStatus("Josh", (float)(50 + 20*Math.sin(Math.toRadians(counter))), 50, false);
			try
			{
				Thread.sleep(10);
			}
			catch (Exception e)
			{

			}
		}
	}



}
