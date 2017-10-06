package com.gray.net;

import com.gray.main.Main;

import java.net.DatagramPacket;

public class BroadcastThread implements Runnable
{
	@Override
	public void run()
	{
		int count = 0;

		while(true)
		{
			try
			{
				count++;
				if(count > 9)
				{
					count = 0;
					String serverData = Server.serverIP + ',' + Server.serverPort;
					DatagramPacket serverInfo = new DatagramPacket(serverData.getBytes(), serverData.length());
					Server.network.sendBroadcast(serverInfo);
				}


				LightCyclesPacket packet = new LightCyclesPacket();
				packet.packPlayerData(Main.game.getPlayers());
				Server.network.sendBroadcast(packet.getPacket());
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				Main.logger.warning("Broadcast Thread Error: " + e.getMessage());
			}
		}
	}
}
