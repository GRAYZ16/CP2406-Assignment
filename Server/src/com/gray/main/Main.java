package com.gray.main;

import com.gray.lightcycles.logic.game.Game;
import com.gray.lightcycles.logic.Util.LightCyclesLogger;
import com.gray.net.Server;

public class Main
{
	public static LightCyclesLogger logger;
	public static Game game;

	public static int gridSize = 100;
	public static final int MAX_PLAYERS = 4;

	public static void main(String[] args)
	{
		game = new Game(gridSize);
		logger = new LightCyclesLogger();
		logger.info("Starting Light Cycles Server");

		game.addPlayer(10,20, "Josh");
		game.addPlayer(50,50, "Sara");

		Server server = new Server();

		server.run();
	}

	public void run()
	{

	}
}
