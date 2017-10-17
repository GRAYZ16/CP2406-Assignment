package com.gray.lightcycles.logic.world;

import java.awt.*;

public class Tile
{
	private boolean isLightWall;
	protected Color color;

	public Tile()
	{
		isLightWall = false;
	}

	public void setLightWall()
	{
		isLightWall = true;
	}

	public boolean isLightWall()
	{
		return isLightWall;
	}

	public void setColor(Color c)
	{
		this.color = c;
	}

}
