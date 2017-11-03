package com.gray.lightcycles.client.render;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.game.GameThread;
import com.gray.lightcycles.client.main.Main;
import com.gray.lightcycles.logic.entity.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class GameWindow extends JPanel
{
	int counter;

	//The JPanel class that handles the game display
	public GameWindow()
	{
		setPreferredSize(new Dimension(Util.WINDOW_WIDTH, Util.WINDOW_HEIGHT));
		setBackground(Color.MAGENTA);
		setDoubleBuffered(true);
		counter = 0;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.drawString("FPS: " + GameThread.lastFPS, 0, 100);
		g2d.translate((Util.WINDOW_WIDTH - (Util.TILE_SIZE * Util.BOARD_SIZE))/2, 0);

		Main.renderer.draw(g);

		for(Map.Entry<String, Player> entry : Main.game.getPlayers().entrySet())
		{
			int color = entry.getKey().getBytes()[0] + entry.getKey().length()*100;
			g2d.setColor(new Color(color % 255));
			g2d.fillRect((int)entry.getValue().getPos().getX() * Util.TILE_SIZE, (int)entry.getValue().getPos().getY() * Util.TILE_SIZE, 2 * Util.TILE_SIZE, Util.TILE_SIZE);
		}


		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}
}
