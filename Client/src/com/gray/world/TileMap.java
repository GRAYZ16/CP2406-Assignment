package com.gray.world;

import com.gray.Util.Util;

import java.awt.*;

public class TileMap
{
	Tile[][] tiles;
	int counter = 0;

	public TileMap()
	{
		tiles = new Tile[Util.BOARD_SIZE][Util.BOARD_SIZE];
		init();
	}

	private void init()
	{
		for(int i = 0; i < Util.BOARD_SIZE; i++)
			for(int j = 0; j < Util.BOARD_SIZE; j++)
			{
				tiles[i][j] = new Tile();
			}
	}

	public void update()
	{
		counter++;
		if(counter < 200)
			tiles[counter][counter].setLightWall();
	}

	public void draw(Graphics g)
	{
		Graphics2D localGraphics = (Graphics2D)g.create();

		for(int i = 0; i < Util.BOARD_SIZE; i++)
			for(int j = 0; j < Util.BOARD_SIZE; j++)
			{
				if(tiles[i][j].isLightWall())
					localGraphics.setColor(Color.BLACK);
				else
					localGraphics.setColor(Color.YELLOW);

				localGraphics.fillRect(i * Util.TILE_SIZE, j * Util.TILE_SIZE, Util.TILE_SIZE, Util.TILE_SIZE);
			}

	}

}
