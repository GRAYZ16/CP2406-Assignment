package com.gray.lightcycleslogic.world;


import java.awt.*;

public class TileMap
{
	private int boardSize;

	Tile[][] tiles;
	int counter = 0;

	public TileMap(int boardSize)
	{
		this.boardSize = boardSize;
		tiles = new Tile[boardSize][boardSize];
		init();
	}

	private void init()
	{
		for(int i = 0; i < boardSize; i++)
			for(int j = 0; j < boardSize; j++)
			{
				tiles[i][j] = new Tile();
			}
	}

	public void update()
	{

	}

	public void setTile(int x, int y)
	{
		tiles[x][y].setLightWall();
	}

	public Tile getTile(int x, int y)
	{
		return tiles[x][y];
	}

	public void draw(Graphics g, int tileSize)
	{
		Graphics2D localGraphics = (Graphics2D)g.create();

		for(int i = 0; i < boardSize; i++)
			for(int j = 0; j < boardSize; j++)
			{
				if(tiles[i][j].isLightWall())
					localGraphics.setColor(new Color(0,191,255));
				else
					localGraphics.setColor(Color.GRAY);

				localGraphics.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
			}

	}

}
