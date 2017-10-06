package com.gray.main;

import com.gray.game.Game;
import com.gray.net.LightCyclesPacket;
import com.gray.net.Network;
import com.gray.net.PacketProtocol;
import com.gray.net.Server;

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

		game.addPlayer(10,10, "Josh");
		game.addPlayer(100,100, "Sara");

		Server server = new Server();
	}

	public void run()
	{

	}
}
