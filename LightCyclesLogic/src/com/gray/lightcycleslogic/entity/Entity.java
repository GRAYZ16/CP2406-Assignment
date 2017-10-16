package com.gray.lightcycleslogic.entity;

import com.gray.lightcycleslogic.math.Vector2d;

public class Entity
{
	//Direction Constants, direction is defined 0 - 3 clockwise from UP position
	public static final int LEFT = -1;
	public static final int RIGHT = 1;

	public static final int DIR_UP = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_DOWN = 2;
	public static final int DIR_RIGHT = 3;

	private Vector2d pos;
	private  Vector2d vel;

	private Vector2d dir;
	private int direction;

	public Entity(Vector2d pos, Vector2d vel)
	{
		this.pos = pos;
		this.vel = vel;
		this.dir = new Vector2d(1,0);
		direction = 1;
	}

	public Entity(double xPos, double yPos, double xVel, double yVel)
	{
		pos = new Vector2d(xPos, yPos);
		vel = new Vector2d(xVel, yVel);
	}

	//Turn
	public void turn(int direction)
	{
		if(direction == LEFT)
		{
			if(this.direction == DIR_UP)
			{
				setDir(DIR_RIGHT);
			}else
			{
				setDir(this.direction - 1);
			}
		}
		else if(direction == RIGHT)
		{
			setDir((this.direction + 1) % 4);
		}
	}

	public void setDir(int dir)
	{
		switch(dir)
		{
			case DIR_UP:
				if(this.direction == 1)
					setPos(new Vector2d(Math.ceil(getPos().getX()), getPos().getY()));
				else
					setPos(new Vector2d(Math.floor(getPos().getX()), getPos().getY()));

				this.dir = new Vector2d(0,1);
				break;
			case DIR_DOWN:
				if(this.direction == 1)
					setPos(new Vector2d(Math.ceil(getPos().getX()), getPos().getY()));
				else
					setPos(new Vector2d(Math.floor(getPos().getX()), getPos().getY()));

				this.dir = new Vector2d(0, -1);
				break;
			case DIR_LEFT:
				if(this.direction == 0)
					setPos(new Vector2d(getPos().getX(), Math.ceil(getPos().getY())));
				else
					setPos(new Vector2d(getPos().getX(), Math.floor(getPos().getY())));

				this.dir = new Vector2d(-1, 0);
				break;
			case DIR_RIGHT:
				if(this.direction == 0)
					setPos(new Vector2d(getPos().getX(), Math.ceil(getPos().getY())));
				else
					setPos(new Vector2d(getPos().getX(), Math.floor(getPos().getY())));

				this.dir = new Vector2d(1, 0);
				break;
		}

		this.direction = dir;
	}

	public void update(double delta)
	{
		Vector2d newPos = getPos().add(getVel().scale(delta).mul(dir));


		setPos(newPos);

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

	public int getDir()
	{
		return direction;
	}

	public Vector2d getVecDir()
	{
		return dir;
	}

	public Vector2d getPos()
	{
		return pos;
	}

	public void setPos(Vector2d pos)
	{
		this.pos = pos;
	}

	public Vector2d getVel()
	{
		return vel;
	}

	public void setVel(Vector2d vel)
	{
		this.vel = vel;
	}
}
