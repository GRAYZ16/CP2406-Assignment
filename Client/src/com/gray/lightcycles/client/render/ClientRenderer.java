package com.gray.lightcycles.client.render;

import java.awt.*;
import java.util.ArrayList;

public class ClientRenderer
{
	private ArrayList<Renderable> objects;

	public ClientRenderer()
	{
		objects = new ArrayList<>();
	}

	public void addObject(Renderable obj)
	{
		objects.add(obj);
	}

	public void removeObject(Renderable obj)
	{
		objects.remove(obj);
	}

	public void draw(Graphics g)
	{
		for(Renderable obj : objects)
		{
			obj.draw(g);
		}
	}

}
