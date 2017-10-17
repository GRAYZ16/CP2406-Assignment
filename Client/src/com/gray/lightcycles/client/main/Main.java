package com.gray.lightcycles.client.main;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.entity.ClientPlayer;
import com.gray.lightcycles.client.net.ClientNetwork;
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

	public static Vector2d windowSize;

	public static void main(String[] args)
	{
		game = new ClientGame(Util.BOARD_SIZE);
		player = new ClientPlayer(new Vector2d(10,10), new Vector2d(0.5, 0.5));
		player.setDir(Player.DIR_RIGHT);
		player.setColor(new Color(0,191, 255));

		renderer = new ClientRenderer();

		renderer.addObject(game.getTiles());
		renderer.addObject(player);

		ClientNetwork network = new ClientNetwork();
		network.run();

		KeyListener listener = new Input();

		frame = new JFrame();
		frame.add(new GameWindow());
		frame.setSize(Util.WINDOW_WIDTH,Util.WINDOW_HEIGHT);
		frame.pack();
		frame.addKeyListener(listener);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setLocationRelativeTo(null);

		(new Thread(new GameThread(frame))).start();


			//for(Map.Entry<String, Player> player: game.getPlayers().entrySet())
			//{
			//	System.out.println("Player: " + player.getKey() + "\nPosition: " + player.getValue().getPos().getX() + ',' + player.getValue().getPos().getY() + "\nisLightWall?: " + player.getValue().isLightWall());
			//}
	}
}
