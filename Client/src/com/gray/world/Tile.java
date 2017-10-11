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

	public void setLightWall()
	{
		isLightWall = true;
	}

	public boolean isLightWall()
	{
		return isLightWall;
	}

}
