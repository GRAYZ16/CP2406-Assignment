package com.gray.net;

import com.gray.entity.Player;
import com.gray.main.Main;

import java.io.IOException;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;

public class PacketProtocol
{
	private final String DATA_FORMAT;
	private int multicastPort;
	private String multicastIP;

	public PacketProtocol(int port, String serverIP)
	{
		this.multicastPort = port;
		this.multicastIP = serverIP;
		DATA_FORMAT = "%s,%f,%f ";
	}

	public byte[] packPlayerData(HashMap<String, Player> data)
	{
		String packetData = "";

		for(Map.Entry<String, Player> player: data.entrySet())
		{
			packetData = packetData.concat(String.format(DATA_FORMAT, player.getKey(), player.getValue().getPos().getX(), player.getValue().getPos().getY()));
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

	public void receiveRequest(String payload)
	{
		String data[] = payload.split(" ");

		switch(data[0])
		{
			case "USER":
				switch(data[2])
				{
					case "TURN":
						Main.logger.info("TURN COMMAND");
						break;
					case "GO":

						break;
					case "JETWALL":

						break;
				}
				break;
			case "ADD":
				break;
			case "REMOVE":
				break;
			case "GRID":
				break;
			case "SAVE":
				break;
			default:
				Main.logger.info("Incorrect Message Received: " + payload);
		}
	}
}
