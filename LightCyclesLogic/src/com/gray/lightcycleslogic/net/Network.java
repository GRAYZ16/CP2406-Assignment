package com.gray.lightcycleslogic.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Network
{
	private DatagramSocket directSocket;


	private MulticastSocket broadcastSocket;
	private int broadcastPort;
	private InetAddress broadcastIP;

	public Network(String broadcastIP, int broadcastPort)
	{
		try
		{
			this.broadcastPort = broadcastPort;
			this.broadcastIP = InetAddress.getByName(broadcastIP);

			directSocket = new DatagramSocket();

			broadcastSocket = new MulticastSocket(broadcastPort);
			broadcastSocket.joinGroup(this.broadcastIP);
		}
		catch(IOException e)
		{
			//main.logger.severe("Exception In creating socket: " + e.getMessage());
		}
	}

	public void sendPacket(String targetIP, int targetPort, String message)
	{
		try
		{
			InetAddress packetAddress = InetAddress.getByName(targetIP);
			DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), packetAddress, targetPort);
			packet.setAddress(InetAddress.getByName(targetIP));
			packet.setPort(targetPort);

			directSocket.send(packet);
		}
		catch(IOException e)
		{
			//main.logger.severe("Could not send packet: " + e.getMessage());
		}
	}

	public DatagramPacket receivePacket()
	{
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		try
		{
			directSocket.receive(packet);
		}
		catch(IOException e)
		{
			//main.logger.severe("Could not receive packet: " + e.getMessage());
		}

		return packet;
	}

	public void sendBroadcast(DatagramPacket packet)
	{
		try
		{
			packet.setAddress(broadcastIP);
			packet.setPort(broadcastPort);

			broadcastSocket.send(packet);
		}
		catch(IOException e)
		{
			//main.logger.severe("Error in Broadcasting: " + e.getMessage());
		}
	}

	public DatagramPacket receiveBroadcast()
	{
		DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

		try
		{
			broadcastSocket.receive(packet);
		}
		catch(IOException e)
		{
			//main.logger.severe("Could not receive packet: " + e.getMessage());
		}

		return packet;
	}

	public String getIP() throws IOException
	{
		return InetAddress.getLocalHost().getHostAddress();
	}

	public int getPort() throws IOException
	{
		return directSocket.getLocalPort();
	}

	public void closeSocket() throws IOException
	{
		broadcastSocket.leaveGroup(broadcastIP);
		broadcastSocket.close();
		directSocket.close();
	}
}
