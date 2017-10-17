package com.gray.lightcycles.client.world;

import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.render.Renderable;
import com.gray.lightcycles.logic.world.Tile;

import java.awt.*;

public class ClientTile extends Tile implements Renderable
{

	public ClientTile()
	{
		super();
		color = Color.MAGENTA;
	}


	@Override
	public void draw(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g.create();

		if(isLightWall())
		{
			g2d.setColor(color);
			g2d.fillRect(0, 0, Util.TILE_SIZE, Util.TILE_SIZE);
		}

		g2d.dispose();
	}

	@Override
	public void setColor(Color c)
	{
		color = c;
	}
}
