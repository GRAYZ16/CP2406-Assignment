package com.gray.entity;

import com.gray.math.Vector2d;

public class Entity
{
	//Direction Constants, direction is defined 0 - 4 clockwise from UP position
	public final int LEFT = -1;
	public final int RIGHT = 1;

	private Vector2d pos;
	private  Vector2d vel;

	private int direction;

	public Entity(Vector2d pos, Vector2d vel)
	{
		this.pos = pos;
		this.vel = vel;
		direction = 0;
	}

	public Entity(double xPos, double yPos, double xVel, double yVel)
	{
		pos = new Vector2d(xPos, yPos);
		vel = new Vector2d(xVel, yVel);
	}

	//Turn
	public void turn(int direction)
	{

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
