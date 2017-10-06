package com.gray.lightcycleslogic.entity;

import com.gray.lightcycleslogic.math.Vector2d;

public class LightWall extends Entity
{
	private float colour[];

	public LightWall(Vector2d pos, float[] colour)
	{
		super(pos, new Vector2d(0,0));
		this.colour = colour;
	}


}
