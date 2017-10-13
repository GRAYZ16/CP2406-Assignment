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

	public void update()
	{
		double newXPos = getPos().getX() + getVel().getX();
		double newYPos = getPos().getY() + getVel().getY();

		setPos(new Vector2d(newXPos, newYPos));

		if(isDead())
		{
			onDeath();
		}
	}

	public boolean isDead()
	{

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
