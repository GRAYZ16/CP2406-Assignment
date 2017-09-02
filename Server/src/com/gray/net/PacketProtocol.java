package com.gray.net;

import com.gray.game.Player;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.net.DatagramPacket;

public class PacketProtocol
{
	private final String DATA_FORMAT = "%d,%d,%d ";
	private int multicastPort;
	private String multicastIP;

	public PacketProtocol(int port, String serverIP)
	{
			this.multicastPort = port;
			this.multicastIP = serverIP;
	}

	public byte[] packPlayerData(List<Player> data)
	{
		String packetData = "";

		for(Player player: data)
		{
			packetData = packetData.concat(String.format(DATA_FORMAT, player.getId(), player.getX(), player.getY()));
		}
		return packetData.getBytes();
	}

	public DatagramPacket generatePacket(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length);

		try
		{
			packet.setAddress(InetAddress.getByName(multicastIP));
			packet.setPort(multicastPort);
		}
		catch(IOException e)
		{
			System.out.println("Error in generating packet: " + e.getMessage());
		}
		return packet;
	}
}
