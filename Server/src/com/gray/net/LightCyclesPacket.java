package com.gray.net;

import com.gray.lightcycleslogic.entity.Player;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class LightCyclesPacket
{
	private final String DATA_FORMAT;

	private DatagramPacket packet;


	public LightCyclesPacket()
	{
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
		return packet;
	}
}
