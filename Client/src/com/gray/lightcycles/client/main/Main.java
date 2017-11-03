package com.gray.lightcycles.client.main;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.entity.ClientPlayer;
import com.gray.lightcycles.client.net.ClientNetwork;
import com.gray.lightcycles.client.net.ServerIOThread;
import com.gray.lightcycles.client.render.ClientRenderer;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Main
{
	public static ClientGame game;
	public static JFrame frame;

	public static ClientRenderer renderer;

	private static ClientPlayer player;
	public static String userName;

	public static boolean serverState;

	public static Vector2d windowSize;

	public static void main(String[] args)
	{
		game = new ClientGame(Util.BOARD_SIZE);

		setPlayer(new ClientPlayer(new Vector2d(0, 0), new Vector2d(0, 0)));
		getPlayer().setColor(new Color(0, 191, 255));

		if(getPlayer().getPos().getX() < Util.BOARD_SIZE / 2)
		{
			getPlayer().setDir(Player.DIR_LEFT);
		}
		else if(getPlayer().getPos().getY() < Util.BOARD_SIZE / 2)
		{
			getPlayer().setDir(Player.DIR_UP);
		}

		userName = "GRAYZ11145";
		serverState = false;

		renderer = new ClientRenderer();

		renderer.addObject(game.getTiles());
		renderer.addObject(getPlayer());

		ClientNetwork network = new ClientNetwork();
		network.run();

		while(ClientNetwork.serverAddress == null)
		{
			try
			{
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		(new Thread(new ServerIOThread("ADD " + userName))).start();

		KeyListener listener = new Input();

		while(!serverState)
		{
			try
			{
				(new Thread(new ServerIOThread("GAME STATE"))).start();
				Thread.sleep(1000);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		frame = new JFrame();
		frame.add(new GameWindow());
		frame.setSize(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT);
		frame.pack();
		frame.addKeyListener(listener);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);

		(new Thread(new GameThread(frame))).start();
	}

	public synchronized static ClientPlayer getPlayer()
	{
		return player;
	}

	public synchronized static void setPlayer(ClientPlayer player)
	{
		Main.player = player;
	}
}
