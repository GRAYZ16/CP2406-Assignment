package com.gray.lightcycles.server.net;

import com.gray.lightcycles.logic.net.LightCyclesPacket;
import com.gray.lightcycles.server.main.Main;

import java.net.DatagramPacket;

public class BroadcastThread implements Runnable
{
	@Override
	public void run()
	{
		int count = 0;

		while(Server.status != Server.GAME_OVER)
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

				if(Server.status != Server.IDLE)
				{
					LightCyclesPacket packet = new LightCyclesPacket(Main.game.getPlayers());
					Server.network.sendBroadcast(packet.getPacket());
				}


				Thread.sleep(100);
			}
			catch(Exception e)
			{
				Main.logger.warning("Broadcast Thread Error: " + e.getMessage());
			}
		}
	}
}
