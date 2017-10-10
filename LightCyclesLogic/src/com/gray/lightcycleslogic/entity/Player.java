package com.gray.lightcycleslogic.entity;

import com.gray.lightcycleslogic.math.Vector2d;

public class Player extends Entity
{
	private boolean isLightWall;


	public Player(Vector2d pos, Vector2d vel)
	{
		super(pos, vel);
		isLightWall = true;
	}

	public Player(Vector2d pos, boolean isLightWall)
	{
		super(pos, new Vector2d(1, 1));
		this.isLightWall = isLightWall;
	}

	public boolean isDead()
	{
		//COLLISION DETECTION
		return false;
	}

	private void onDeath()
	{
		//Death Animation

		this.setVel(new Vector2d(0,0));
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