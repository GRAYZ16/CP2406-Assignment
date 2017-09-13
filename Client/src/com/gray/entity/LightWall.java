package com.gray.entity;

import com.gray.math.Vector2d;

import java.util.Vector;

public class LightWall extends Entity
{
	private float colour[];

	public LightWall(Vector2d pos, float[] colour)
	{
		super(pos, new Vector2d(0,0));
		this.colour = colour;
	}


}
