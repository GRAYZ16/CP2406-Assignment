package com.gray.main;

import com.gray.lightcycles.logic.game.Game;
import com.gray.lightcycles.logic.Util.LightCyclesLogger;
import com.gray.net.Server;

public class Main
{
	public static LightCyclesLogger logger;
	public static Game game;

	public static int gridSize = 100;
	public static final int MIN_PLAYERS = 3;

	public static void main(String[] args)
	{
		game = new Game(gridSize);
		logger = new LightCyclesLogger();
		logger.info("Starting Light Cycles Server");

		Server server = new Server();

		server.run();
	}

	public void run()
	{

	}
}
