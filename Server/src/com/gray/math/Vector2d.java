package com.gray.math;

public class Vector2d
{
	private double x;
	private double y;

	public Vector2d(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public void add(Vector2d vector)
	{
		this.x += vector.getX();
		this.y += vector.getY();
	}

	public void scale(double scale)
	{
		this.x = x * scale;
		this.y = y * scale;
	}

	public void set(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
}
