package com.gray.lightcycleslogic.net;

import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.math.Vector2d;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;

public class LightCyclesPacket
{
	private final String DATA_FORMAT = "%s,%f,%f,%b ";

	private DatagramPacket packet;


	public LightCyclesPacket(HashMap<String, Player> data)
	{
		packPlayerData(data);
	}

	public LightCyclesPacket(DatagramPacket packet)
	{
		this.packet = packet;
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

	public HashMap<String, Player> getPlayerData()
	{
		HashMap<String, Player> players = new HashMap<>();
		String[] playerStrings = new String(packet.getData(), packet.getOffset(), packet.getLength()).split(" ");

		for(String playerString : playerStrings)
		{
			String[] playerData = playerString.split(",");

			players.put(playerData[0], new Player(new Vector2d(Float.parseFloat(playerData[1]), Float.parseFloat(playerData[2])), Boolean.parseBoolean(playerData[3])));
		}

		return players;
	}

	public DatagramPacket getPacket()
	{
		return packet;
	}
}
