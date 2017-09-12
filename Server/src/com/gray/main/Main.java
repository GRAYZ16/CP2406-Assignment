package com.gray.main;

import com.gray.game.Game;
import com.gray.game.Player;
import com.gray.net.NetworkHandler;
import com.gray.net.PacketProtocol;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main
{
	public static final int PLAYER_SIZE = 20;
	static Logger logger;

	public static void main(String[] args)
	{
		Game game = new Game();
		logger = Logger.getLogger("ServerLogger");
		logger.info("Starting Light Cycles Server");
		
		PacketProtocol protocol = new PacketProtocol(25535, "230.1.1.1");
		NetworkHandler network = new NetworkHandler(PLAYER_SIZE);

		game.addPlayer(10,10);
		game.addPlayer(100,100);

		byte[] data = protocol.packPlayerData(game.getPlayers());
		network.sendPacket(protocol.generatePacket(data));
		System.out.println(network.receivePacket());


	}

}
