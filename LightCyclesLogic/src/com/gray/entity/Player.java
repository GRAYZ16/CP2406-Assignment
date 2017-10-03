package com.gray.entity;

import com.gray.math.Vector2d;

public class Player extends Entity
{
	private boolean isLightWall;
	private float colour[];

	public Player(Vector2d pos, Vector2d vel, float[] colour)
	{
		super(pos, vel);
		this.colour = colour;
		isLightWall = true;
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

	private boolean isLightWall()
	{
		return isLightWall;
	}
}
