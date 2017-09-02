package com.gray.game;

public class Player
{
	private int x;
	private int y;
	private boolean isJetWall;
	private float lastUpdate;
	private int id;

	public Player(int id, int x, int y, boolean isJetWall)
	{
		this.id = id;
		this.x = x;
		this.y = y;
		this.isJetWall = isJetWall;
		//TODO: Implement lastUpdate Time to check if users have dropped
	}

	public int getId()
	{
		return id;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public boolean isJetWall()
	{
		return isJetWall;
	}

	public void setJetWall(boolean jetWall)
	{
		isJetWall = jetWall;
	}

	public float getLastUpdate()
	{
		return lastUpdate;
	}

	public void setLastUpdate(float lastUpdate)
	{
		this.lastUpdate = lastUpdate;
	}
}
