package com.gray.lightcycles.logic.entity;

import com.gray.lightcycles.logic.math.Vector2d;

import java.util.Vector;

public class Entity
{
	//Direction Constants, direction is defined 0 - 3 clockwise from UP position
	public static final int LEFT = -1;
	public static final int RIGHT = 1;

	public static final int DIR_UP = 0;
	public static final int DIR_RIGHT = 1;
	public static final int DIR_DOWN = 2;
	public static final int DIR_LEFT = 3;

	private Vector2d pos;
	private  Vector2d vel;

	private boolean isDead;

	private Vector2d dir;
	private int direction;

	public Entity(Vector2d pos, Vector2d vel)
	{
		this.pos = pos;
		this.vel = vel;
		this.dir = new Vector2d(1,0);
		direction = 1;
		isDead = false;
	}

	public Entity(Vector2d pos, Vector2d vel, int direction)
	{
		this.pos = pos;
		this.vel = vel;
		this.direction = direction;
		setDir(direction);
		isDead = false;
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
				setDir(DIR_LEFT);
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
				if(this.direction == DIR_RIGHT)
					setPos(new Vector2d(Math.ceil(getPos().getX()), getPos().getY()));
				else
					setPos(new Vector2d(Math.floor(getPos().getX()), getPos().getY()));

				this.dir = new Vector2d(0,1);
				break;
			case DIR_DOWN:
				if(this.direction == DIR_RIGHT)
					setPos(new Vector2d(Math.ceil(getPos().getX()), getPos().getY()));
				else
					setPos(new Vector2d(Math.floor(getPos().getX()), getPos().getY()));

				this.dir = new Vector2d(0, -1);
				break;
			case DIR_LEFT:
				if(this.direction == DIR_UP)
					setPos(new Vector2d(getPos().getX(), Math.ceil(getPos().getY())));
				else
					setPos(new Vector2d(getPos().getX(), Math.floor(getPos().getY())));

				this.dir = new Vector2d(1, 0);
				break;
			case DIR_RIGHT:
				if(this.direction == DIR_UP)
					setPos(new Vector2d(getPos().getX(), Math.ceil(getPos().getY())));
				else
					setPos(new Vector2d(getPos().getX(), Math.floor(getPos().getY())));

				this.dir = new Vector2d(-1, 0);
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

	public Vector2d nextPos()
	{
		return getPos().add(getVel().mul(dir).scale(1 / getVel().getX()));
	}

	public boolean isDead()
	{

		return isDead;
	}

	public void kill()
	{
		isDead = true;
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
