package com.gray.lightcycles.client.game;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.gray.lightcycles.client.world.ClientTileMap;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;

public class ClientGame
{
	private final float VEL = 0.4f;
	private int boardSize;
	private  HashMap<String, Player> players;
	private  HashMap<String, Color> colors;
	private ClientTileMap tiles;

	public ClientGame(int boardSize)
	{
		players = new HashMap<>();
		colors = new HashMap<>();
		tiles = new ClientTileMap(boardSize);
		this.boardSize = boardSize;
	}

	public synchronized void addPlayer(double x, double y, String name)
	{
		Random random = new Random();
		int dir = 1;

		//Face the player towards the centre of the board
		if(x < boardSize / 2)
		{
			dir = 3;
		}
		else if(y < boardSize / 2)
		{
			dir = 0;
		}

		players.put(name, new Player( new Vector2d(x, y), new Vector2d(VEL, VEL), dir));
		colors.put(name, new Color(random.nextInt(255)));
	}

	public synchronized HashMap<String, Player> getPlayers()
	{
		return players;
	}

	public synchronized Player getPlayer(String name)
	{
		return players.get(name);
	}

	public synchronized void setPlayers(HashMap<String, Player> players)
	{
		this.players = players;
	}

	public synchronized void update(double delta)
	{

		//Calculate the update for each player and check collisions
		for(Map.Entry<String, Player> entry : players.entrySet())
		{
			Vector2d lastPos = entry.getValue().getPos();
			entry.getValue().update(delta);
			checkDeath(entry.getValue());

			if(!entry.getValue().isDead())
			{
				setTile((int)Math.round(lastPos.getX()), (int)Math.round(lastPos.getY()), colors.get(entry.getKey()));
			}
		}
	}

	public synchronized void checkDeath(Player player)
	{
		Vector2d nextPos = player.nextPos();

		//Calculates whether the player will hit the walls of the board on next update
		if(nextPos.getX() > boardSize || nextPos.getX() < 0 || nextPos.getY() > boardSize ||nextPos.getY() < 0)
		{
			if(!player.isDead())
			{
				player.kill();
			}
		}
		//Also check if the player will hit a light wall
		else if(tiles.getTile((int)Math.floor(nextPos.getX()), (int)Math.floor(nextPos.getY())).isLightWall())
		{
			player.kill();
		}
	}

	public synchronized ClientTileMap getTiles()
	{
		return tiles;
	}

	public synchronized void setTile(int x, int y, Color color)
	{
		tiles.setTile(x,y, color);
	}
}
