package com.gray.net;

import com.gray.entity.Player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class LightCyclesPacket
{
	private final String DATA_FORMAT;
	private int multicastPort;
	private String multicastIP;

	private DatagramPacket packet;


	public LightCyclesPacket(int port, String IP)
	{
		this.multicastPort = port;
		this.multicastIP = IP;
		DATA_FORMAT = "%s,%f,%f,%b ";

	}

	public void packPlayerData(HashMap<String, Player> data)
	{
		String packetData = "";

		for(Map.Entry<String, Player> player: data.entrySet())
		{
			packetData = packetData.concat(String.format(DATA_FORMAT, player.getKey(), player.getValue().getPos().getX(), player.getValue().getPos().getY(), player.getValue().isLightWall()));
		}
		packet = new DatagramPacket(packetData.getBytes(), packetData.length());
	}

	public DatagramPacket getPacket()
	{
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
