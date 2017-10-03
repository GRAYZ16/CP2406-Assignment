package com.gray.main;

import com.gray.game.Game;
import com.gray.net.LightCyclesPacket;
import com.gray.net.NetworkHandler;
import com.gray.net.PacketProtocol;

import java.util.logging.Logger;

public class Main
{
	public static Logger logger;
	public static Game game;

	public static void main(String[] args)
	{
		game = new Game();
		logger = Logger.getLogger("ServerLogger");
		logger.info("Starting Light Cycles Server");
		
		PacketProtocol protocol = new PacketProtocol(25535, "230.1.1.1");
		NetworkHandler network = new NetworkHandler();

		LightCyclesPacket packet = new LightCyclesPacket(25535, "230.1.1.1");


		game.addPlayer(10,10, "Josh");
		game.addPlayer(100,100, "Sara");

		packet.packPlayerData(game.getPlayers());

		network.sendPacket(packet.getPacket());

		while(true)
		{
			protocol.receiveRequest(network.receivePacket());
		}
	}

	public void run()
	{

	}
}
