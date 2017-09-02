package com.gray.net;

import java.net.DatagramPacket;

public class PacketHandler implements Runnable
{
	private DatagramPacket packet;

	public PacketHandler(DatagramPacket packet)
	{
		this.packet = packet;
	}

	@Override
	public void run()
	{

	}
}
