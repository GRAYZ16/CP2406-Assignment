package com.gray.main;

import com.gray.lightcycleslogic.game.Game;
import com.gray.lightcycleslogic.Util.LightCyclesLogger;
import com.gray.net.Server;

public class Main
{
	public static LightCyclesLogger logger;
	public static Game game;

	public static final int BOARD_SIZE = 200;

	public static void main(String[] args)
	{
		game = new Game(BOARD_SIZE);
		logger = new LightCyclesLogger();
		logger.info("Starting Light Cycles Server");

		game.addPlayer(10,10, "Josh");
		game.addPlayer(100,100, "Sara");

		Server server = new Server();

		server.run();

	}

	public void run()
	{
	}
}
