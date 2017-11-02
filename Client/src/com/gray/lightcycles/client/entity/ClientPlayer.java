package com.gray.lightcycles.client.entity;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.main.Main;
import com.gray.lightcycles.client.render.Renderable;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;

import java.awt.*;

public class ClientPlayer extends Player implements Renderable
{
	private Color color;

	public ClientPlayer(Vector2d pos, Vector2d vel)
	{
		super(pos, vel);
		color = Color.BLACK;
	}

	public ClientPlayer(Vector2d pos, Vector2d vel, int dir)
	{
		super(pos, vel, dir);
		color = Color.BLACK;
	}


	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(this.color);

		double rectX = Main.player.getPos().getX() * Util.TILE_SIZE - Util.TILE_SIZE / 2 -   (Util.TILE_SIZE / 2) * Math.abs(Main.player.getVecDir().getX());
		double rectY = Main.player.getPos().getY() * Util.TILE_SIZE - Util.TILE_SIZE / 2 -   (Util.TILE_SIZE / 2) * Math.abs(Main.player.getVecDir().getY());

		double width = 2 * (Util.TILE_SIZE + Math.abs(Main.player.getVecDir().getX()) * Util.TILE_SIZE);
		double height = 2 * (Util.TILE_SIZE + Math.abs(Main.player.getVecDir().getY()) * Util.TILE_SIZE);


		g2d.fillRect((int)rectX, (int)rectY, (int)width, (int)height);

		g2d.setColor(this.color.darker());
		g2d.drawRect((int)rectX, (int)rectY, (int)width, (int)height);
	}

	public void setColor(Color c)
	{
		this.color = c;
	}

	public Color getColor()
	{
		return color;
	}
}
