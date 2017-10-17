package com.gray.lightcycles.client.net;

import com.gray.lightcycles.client.main.Main;
import com.gray.lightcycles.logic.net.LightCyclesPacket;

import java.net.DatagramPacket;

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
				Main.game.setPlayers(playerPacket.getPlayerData());
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
