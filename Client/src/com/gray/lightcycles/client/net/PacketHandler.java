package com.gray.lightcycles.client.net;

import com.gray.lightcycles.client.main.Main;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.lightcycles.logic.net.LightCyclesPacket;

import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.Map;

public class PacketHandler implements  Runnable
{

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
			if(payload.split(" ").length > 1)
			{
				LightCyclesPacket playerPacket = new LightCyclesPacket(packet);

				HashMap<String, Player> players = playerPacket.getPlayerData();

				for(Map.Entry<String, Player> entry : players.entrySet())
				{



					if(entry.getKey().equals(Main.userName))
					{
						Main.player.setPos(entry.getValue().getPos());
					}
					else
					{
						if(Main.game.getPlayer(entry.getKey()) == null)
						{
							Main.game.addPlayer(entry.getValue().getPos().getX(), entry.getValue().getPos().getY(), entry.getKey());
						}
						else
						{
							Main.game.setPlayerStatus(entry.getKey(), entry.getValue().getPos().getX(), entry.getValue().getPos().getY(), entry.getValue().isLightWall());
						}

					}

				}
			}
			else
			{
				String[] recievedData = payload.split(",");

				if(recievedData.length == 2)
				{
					ClientNetwork.serverAddress = recievedData[0];
					ClientNetwork.serverPort = Integer.parseInt(recievedData[1]);
				}

			}
		}
	}
}
