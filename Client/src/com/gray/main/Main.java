package com.gray.main;

import com.gray.Util.Util;
import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.game.Game;
import com.gray.lightcycleslogic.math.Vector2d;
import com.gray.net.ClientNetwork;
import com.gray.world.TileMap;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.Map;

public class Main
{
	public static Game game;
	public static JFrame frame;

	public static Vector2d windowSize;

	public static TileMap tiles;

	public static void main(String[] args)
	{
		game = new Game();
		tiles = new TileMap();

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
