package com.gray.lightcycles.client.main;

import com.gray.lightcycles.client.Util.Util;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel
{
	int counter;

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
		g2d.setColor(Color.BLACK);
		g2d.drawString("FPS: " + GameThread.lastFPS, 0, 100);
		g2d.translate((Util.WINDOW_WIDTH - (Util.TILE_SIZE * Util.BOARD_SIZE))/2, 0);

		Main.renderer.draw(g);

		g2d.setColor(Color.YELLOW);
		g2d.fillRect((int)Main.game.getPlayer("Josh").getPos().getX() * Util.TILE_SIZE, (int)Main.game.getPlayer("Josh").getPos().getY() * Util.TILE_SIZE, 2 * Util.TILE_SIZE, Util.TILE_SIZE);
		g2d.drawString("Josh", (int)Main.game.getPlayer("Josh").getPos().getX() * Util.TILE_SIZE + Util.TILE_SIZE, (int)Main.game.getPlayer("Josh").getPos().getY() * Util.TILE_SIZE + Util.TILE_SIZE);

		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}
}
