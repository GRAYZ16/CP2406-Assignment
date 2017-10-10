package com.gray.world;

import java.awt.*;

public class Tile
{
	private Color color;

	private boolean isLightWall;


	public Tile()
	{
		isLightWall = false;
	}

	public void draw(Graphics2D graphics)
	{
		Graphics localg = graphics.create();
		localg.setColor(color);
		localg.drawRect(32,32));
	}

}
