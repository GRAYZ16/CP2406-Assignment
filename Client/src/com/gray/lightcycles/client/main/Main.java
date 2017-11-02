package com.gray.lightcycles.client.main;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.entity.ClientPlayer;
import com.gray.lightcycles.client.net.ClientNetwork;
import com.gray.lightcycles.client.net.ServerIOThread;
import com.gray.lightcycles.client.render.ClientRenderer;
import com.gray.lightcycles.client.world.ClientTile;
import com.gray.lightcycles.client.world.ClientTileMap;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.game.Game;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.lightcycles.logic.world.Tile;
import com.gray.lightcycles.logic.world.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class Main
{
	public static ClientGame game;
	public static JFrame frame;

	public static ClientRenderer renderer;

	public static ClientPlayer player;
	public static String userName;

	public static boolean serverState;

	public static Vector2d windowSize;

	public static void main(String[] args)
	{
		game = new ClientGame(Util.BOARD_SIZE);
		player = new ClientPlayer(new Vector2d(10, 10), new Vector2d(0.5, 0.5));
		player.setDir(Player.DIR_RIGHT);
		player.setColor(new Color(0, 191, 255));

		userName = "Evie1";
		serverState = false;

		renderer = new ClientRenderer();

		renderer.addObject(game.getTiles());
		renderer.addObject(player);

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

		frame = new JFrame();
		frame.add(new GameWindow());
		frame.setSize(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT);
		frame.pack();
		frame.addKeyListener(listener);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);

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

		(new Thread(new GameThread(frame))).start();
	}
}
