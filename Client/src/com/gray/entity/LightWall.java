package com.gray.entity;

import com.gray.math.Vector2d;

import java.util.Vector;

public class LightWall extends Entity
{
	private  Player parent;

	public LightWall(Vector2d pos, Player parent)
	{
		super(pos, new Vector2d(0,0));
		this.parent = parent;
	}


}
