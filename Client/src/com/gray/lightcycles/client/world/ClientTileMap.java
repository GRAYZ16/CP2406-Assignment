package com.gray.lightcycles.client.world;


import com.gray.lightcycles.client.Util.Util;
import com.gray.lightcycles.client.render.Renderable;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ClientTileMap implements Renderable
{
	private int boardSize;

	ClientTile[][] tiles;
	int counter = 0;

	public ClientTileMap(int boardSize)
	{
		this.boardSize = boardSize;
		tiles = new ClientTile[boardSize][boardSize];
		init();
	}

	private void init()
	{
		for(int i = 0; i < boardSize; i++)
			for(int j = 0; j < boardSize; j++)
			{
				tiles[i][j] = new ClientTile();
			}
	}

	public void update()
	{

	}

	public void setTile(int x, int y, Color c)
	{
		if(x < boardSize && y < boardSize)
		{
			tiles[x][y].setLightWall();
			tiles[x][y].setColor(c);
		}
	}

	public ClientTile getTile(int x, int y)
	{
		return tiles[x][y];
	}

	public void draw(Graphics g)
	{
		Graphics2D localGraphics = (Graphics2D)g.create();

		localGraphics.setColor(Color.GRAY);
		localGraphics.fillRect(0,0, Util.WINDOW_HEIGHT, Util.WINDOW_HEIGHT);

		for(int i = 0; i < Util.BOARD_SIZE; i++)
			for(int j = 0; j < Util.BOARD_SIZE; j++)
			{
				g.translate(i * Util.TILE_SIZE, j * Util.TILE_SIZE);

				tiles[i][j].draw(g);

				g.translate(-i * Util.TILE_SIZE, -j * Util.TILE_SIZE);
			}

		localGraphics.dispose();
		for(int i = 0; i < Util.BOARD_SIZE; i++)
			for(int j = 0; j < Util.BOARD_SIZE; j++)
			{
				tiles[i][j].draw(g);
			}
	}
}
