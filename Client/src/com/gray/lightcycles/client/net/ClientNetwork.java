package com.gray.lightcycles.client.net;

import com.gray.lightcycles.logic.net.Network;

public class ClientNetwork
{
	public static final String BROADCAST_IP = "230.1.1.1";
	public static final int BROADCAST_PORT = 25535;

	public static String serverAddress;
	public static int serverPort;

	public static Network network;

	//Client Network Wrapper
	public ClientNetwork()
	{
		network = new Network(BROADCAST_IP, BROADCAST_PORT);
	}

	public void run()
	{
		(new Thread(new PacketHandler())).start();
	}
}
