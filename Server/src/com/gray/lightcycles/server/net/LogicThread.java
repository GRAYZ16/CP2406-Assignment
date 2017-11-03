package com.gray.lightcycles.server.net;

import com.gray.lightcycles.server.main.Main;
import com.gray.lightcycles.server.scores.Scoreboard;

public class LogicThread implements Runnable
{
	public static boolean isRunning;

	public LogicThread()
	{
		isRunning = true;
	}


	@Override
	public void run()
	{
		//Find the current time at beginning of execution
		long lastTime = System.nanoTime();

		int score = 0;

		//The desired time per frame in ns to be able to stabilise at FPS_CAP
		final long TARGET_TIME = 1000000000 / 20;

		while(isRunning)
		{
			//Calculate the time taken to update the frame and reset the last frame time
			long currentTime = System.nanoTime();
			long updateTime = currentTime - lastTime;
			lastTime = currentTime;

			//Calculate the time step for physics updating (Player movement etc)
			double delta = updateTime / ((double)TARGET_TIME);


			Main.game.getTiles().update();

			Main.game.update(delta);

			if(Main.game.getPlayers().size() == 1)
			{
				isRunning = false;
				Server.score = score;
				Server.winner = (String)Main.game.getPlayers().keySet().toArray()[0];

				Scoreboard.newScore(Server.winner, Server.score);
			}

			score++;

			try
			{
				Thread.sleep((lastTime - System.nanoTime() + TARGET_TIME) / 1000000);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
