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
		g2d.drawString("FPS: " + Main.lastFPS,100,100);
		counter ++;
		g2d.fillRect(300, (int)(300 + 100 * Math.sin(Math.toRadians(counter))), 50, 50);


		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}
}
