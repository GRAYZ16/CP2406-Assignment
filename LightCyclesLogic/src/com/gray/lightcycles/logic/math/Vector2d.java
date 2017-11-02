package com.gray.lightcycles.logic.math;

public class Vector2d
{
	private double x;
	private double y;

	public Vector2d(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public Vector2d add(Vector2d vector)
	{
		Vector2d vec = new Vector2d(0,0);

		vec.set(x + vector.getX(), y + vector.getY());

		return vec;
	}

	public Vector2d mul(Vector2d vector)
	{
		Vector2d vec = new Vector2d(0,0);
		vec.set(x * vector.getX(), y * vector.getY());

		return vec;
	}

	public Vector2d scale(double scale)
	{
		return new Vector2d(x * scale, y * scale);
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

	public boolean equals(Vector2d vector)
	{
		return (x == vector.getX() && y == vector.getY());
	}

	public double magnitude()
	{
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}
