package com.gray.lightcycles.server.net;

import com.gray.lightcycles.server.main.Main;

import java.net.DatagramPacket;

public class ReceiveThread implements Runnable
{
	@Override
	public void run()
	{
		while(true)
		{
			Main.logger.info("WAITING");
			DatagramPacket packet = Server.network.receivePacket();
			(new Thread(new ClientThread(packet))).start();
		}
	}
}
