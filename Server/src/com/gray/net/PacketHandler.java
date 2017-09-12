package com.gray.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class PacketHandler implements Runnable
{
	private DatagramSocket socket;

	public PacketHandler(DatagramSocket socket)
	{
		this.socket = socket;
	}

	@Override
	public void run()
	{
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
		try
		{
			socket.receive(packet);
		}
		catch(IOException e)
		{
			System.out.println("Error in Receiving Packet");
		}
	}
}
