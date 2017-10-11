package com.gray.main;

import com.gray.Util.Util;

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
		setBackground(Color.MAGENTA);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawString("FPS: " + GameThread.lastFPS, 0, 100);
		g2d.translate((Util.WINDOW_WIDTH - (Util.TILE_SIZE * Util.BOARD_SIZE))/2, 0);
		Main.tiles.draw(g);

		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}
}
