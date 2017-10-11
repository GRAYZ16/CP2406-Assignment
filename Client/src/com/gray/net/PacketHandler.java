package com.gray.net;

import com.gray.lightcycleslogic.net.LightCyclesPacket;
import com.gray.main.Main;

import java.net.DatagramPacket;
import java.net.InetAddress;

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
