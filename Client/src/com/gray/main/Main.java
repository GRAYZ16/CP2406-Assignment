package com.gray.main;

import com.gray.Util.Util;
import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.game.Game;
import com.gray.net.ClientNetwork;

import javax.swing.*;
import java.util.Map;

public class Main
{
	public static Game game;
	public static boolean isRunning;
	public static JFrame frame;
	public static int lastFPS = 0;

	public static void main(String[] args)
	{
		game = new Game();
		isRunning = true;

		ClientNetwork network = new ClientNetwork();
		network.run();

		frame = new JFrame();
		frame.add(new GameWindow());
		frame.setSize(Util.WINDOW_WIDTH,Util.WINDOW_HEIGHT);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		Main main = new Main();
		main.run();

			//for(Map.Entry<String, Player> player: game.getPlayers().entrySet())
			//{
			//	System.out.println("Player: " + player.getKey() + "\nPosition: " + player.getValue().getPos().getX() + ',' + player.getValue().getPos().getY() + "\nisLightWall?: " + player.getValue().isLightWall());
			//}
	}

	public void run()
	{
		//Find the current time at beginning of execution
		long lastTime = System.nanoTime();

		//The desired time per frame in ns to be able to stabilise at FPS_CAP
		final long TARGET_TIME = 1000000000 / Util.FPS_CAP;


		long fpsTime = 0;
		int fpsCounter = 0;

		while(isRunning)
		{
			//Calculate the time taken to update the frame and reset the last frame time
			long currentTime = System.nanoTime();
			long updateTime = currentTime - lastTime;
			lastTime = currentTime;

			//Calculate the time step for physics updating (Player movement etc)
			double delta = updateTime / ((double)TARGET_TIME);

			fpsTime += updateTime;
			fpsCounter++;

			if(fpsTime > 1000000000)
			{
				lastFPS = fpsCounter;
				System.out.println("FPS: " + fpsCounter);
				fpsCounter = 0;
				fpsTime = 0;
			}

			//update physics and redraw
			frame.repaint();

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
