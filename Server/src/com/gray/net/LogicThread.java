package com.gray.net;

import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.main.Main;

import java.util.HashMap;

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

		//The desired time per frame in ns to be able to stabilise at FPS_CAP
		final long TARGET_TIME = 1000000000 / 60;

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

			try
			{
				Thread.sleep((lastTime - System.nanoTime() + TARGET_TIME)/1000000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
