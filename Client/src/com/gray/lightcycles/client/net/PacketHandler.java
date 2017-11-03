package com.gray.lightcycles.client.net;

import com.gray.lightcycles.client.main.Main;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.net.LightCyclesPacket;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;

public class PacketHandler implements  Runnable
{

	//Thread that receives and handles broadcasts sent by the server
	public PacketHandler()
	{

	}

	@Override
	public void run()
	{
		while(true)
		{
			DatagramPacket packet = ClientNetwork.network.receiveBroadcast();
			String payload = new String(packet.getData(), packet.getOffset(), packet.getLength());

			//Determine whether the information is game data or network information
			if(payload.split(" ").length > 1)
			{
				LightCyclesPacket playerPacket = new LightCyclesPacket(packet);

				HashMap<String, Player> players = playerPacket.getPlayerData();
				HashMap<String, Player> newPlayers = new HashMap<>();

				for(Map.Entry<String, Player> entry : players.entrySet())
				{

					if(entry.getKey().equals(Main.userName))
					{
						Main.getPlayer().setPos(entry.getValue().getPos());
					}
					else
					{
						if(Main.game.getPlayer(entry.getKey()) == null)
						{
							newPlayers.put(entry.getKey(), entry.getValue());
						}
						else
						{
							newPlayers.put(entry.getKey(), entry.getValue());
						}
					}
				}
				Main.game.setPlayers(newPlayers);
			}
			else
			{
				String[] receivedData = payload.split(",");

				if(receivedData.length == 2)
				{
					ClientNetwork.serverAddress = receivedData[0];
					ClientNetwork.serverPort = Integer.parseInt(receivedData[1]);
				}

			}
		}
	}
}
