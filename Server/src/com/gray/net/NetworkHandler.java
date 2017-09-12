package com.gray.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkHandler implements Runnable
{
	private DatagramSocket udpSocket;
	private ExecutorService executorService;

	public NetworkHandler(int poolSize)
	{
		try
		{
			udpSocket = new DatagramSocket();
			executorService = Executors.newFixedThreadPool(poolSize);
		}catch(IOException e)
		{
			System.out.println("Exception In creating socket: " + e.getMessage());
		}
	}

	public void sendPacket(DatagramPacket packet)
	{
		try
		{
			udpSocket.send(packet);
		}
		catch(IOException e)
		{
			System.out.println("Could not send packet: " + e.getMessage());
		}
	}

	public String receivePacket()
	{
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		try
		{
			udpSocket.receive(packet);
		}
		catch(IOException e)
		{
			System.out.println("Could not receive packet: " + e.getMessage());
		}

		return new String(packet.getData(), packet.getOffset(), packet.getLength());
	}

	public void closeSocket()
	{
		udpSocket.close();
	}

	@Override
	public void run()
	{
		try
		{
			while(true)
			{
				executorService.execute(new PacketHandler(udpSocket));
			}
		}
		catch(Exception e)
		{

		}
	}
}
