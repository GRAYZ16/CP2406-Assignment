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
		setBackground(Color.BLACK);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.drawString("FPS: " + GameThread.lastFPS, 0, 100);
		g2d.translate((Util.WINDOW_WIDTH - (Util.TILE_SIZE * Util.BOARD_SIZE))/2, 0);
		Main.game.getTiles().draw(g, Util.TILE_SIZE);

		g2d.fillRect((int)Main.game.getPlayer("Josh").getPos().getX() * Util.TILE_SIZE, (int)Main.game.getPlayer("Josh").getPos().getY() * Util.TILE_SIZE, 2 * Util.TILE_SIZE, Util.TILE_SIZE);

		double rectX = Main.player.getPos().getX() * Util.TILE_SIZE - Util.TILE_SIZE / 2 -   (Util.TILE_SIZE / 2) * Math.abs(Main.player.getVecDir().getX());

		double rectY = Main.player.getPos().getY() * Util.TILE_SIZE - Util.TILE_SIZE / 2 -   (Util.TILE_SIZE / 2) * Math.abs(Main.player.getVecDir().getY());



		double width = 2 * (Util.TILE_SIZE + Math.abs(Main.player.getVecDir().getX()) * Util.TILE_SIZE);
		double height = 2 * (Util.TILE_SIZE + Math.abs(Main.player.getVecDir().getY()) * Util.TILE_SIZE);


		System.out.println((int)rectX + ", " + (int)rectY + ", " + (int)width + ", " + (int)height);


		g2d.setColor(new Color(0,191,255));
		g2d.fillRect((int)rectX, (int)rectY, (int)width, (int)height);

		g2d.setColor(Color.BLUE);
		g2d.drawRect((int)rectX, (int)rectY, (int)width, (int)height);

		Toolkit.getDefaultToolkit().sync();
		g2d.dispose();
	}
}
