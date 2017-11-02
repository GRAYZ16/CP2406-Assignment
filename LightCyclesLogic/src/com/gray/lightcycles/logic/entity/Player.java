package com.gray.lightcycles.logic.entity;

import com.gray.lightcycles.logic.math.Vector2d;

public class Player extends Entity
{
	private boolean isLightWall;


	public Player(Vector2d pos, Vector2d vel)
	{
		super(pos, vel);
		isLightWall = true;
	}

	public Player(Vector2d pos, Vector2d vel, int direction)
	{
		super(pos, vel, direction);
		isLightWall = true;
	}

	public Player(Vector2d pos, boolean isLightWall)
	{
		super(pos, new Vector2d(1, 1));
		this.isLightWall = isLightWall;
	}

	public boolean isLightWall()
	{
		return isLightWall;
	}

	public void setLightWall(boolean isLightWall)
	{
		this.isLightWall = isLightWall;
	}

}
