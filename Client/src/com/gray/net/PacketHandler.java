package com.gray.net;

import com.gray.lightcycleslogic.net.LightCyclesPacket;
import com.gray.main.Main;

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
		}
	}
}
