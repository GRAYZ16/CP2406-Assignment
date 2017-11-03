package com.gray.lightcycles.client.entity;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.main.Main;
import com.gray.lightcycles.client.render.Renderable;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;

import java.awt.*;

//Client Version of the Player class to handle drawing
public class ClientPlayer extends Player implements Renderable
{
	private Color color;

	public ClientPlayer(Vector2d pos, Vector2d vel)
	{
		super(pos, vel);
		color = Color.BLACK;
	}

	@Override
	public void draw(Graphics g)
	{
		//This function draws the player with the player position being the centre of the player
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setColor(this.color);

		//Finding the position of the top left corner of the player based on direction and position
		double rectX = Main.getPlayer().getPos().getX() * Util.TILE_SIZE - Util.TILE_SIZE / 2 - (Util.TILE_SIZE / 2) * Math.abs(Main.getPlayer().getVecDir().getX());
		double rectY = Main.getPlayer().getPos().getY() * Util.TILE_SIZE - Util.TILE_SIZE / 2 - (Util.TILE_SIZE / 2) * Math.abs(Main.getPlayer().getVecDir().getY());


		//Calculate the width and height of the player based on the direction
		double width = 2 * (Util.TILE_SIZE + Math.abs(Main.getPlayer().getVecDir().getX()) * Util.TILE_SIZE);
		double height = 2 * (Util.TILE_SIZE + Math.abs(Main.getPlayer().getVecDir().getY()) * Util.TILE_SIZE);

		//Fill the body of the player
		g2d.fillRect((int)rectX, (int)rectY, (int)width, (int)height);

		//outline the player in a darker shade
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
