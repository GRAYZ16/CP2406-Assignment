package com.gray.lightcycles.client.main;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.gray.lightcycles.client.world.ClientTileMap;
import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.lightcycles.logic.world.TileMap;

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
		Vector2d playerPos = player.getPos();

		if(player.nextPos().getX() > boardSize || player.nextPos().getX() < 0 || player.nextPos().getY() > boardSize || player.nextPos().getY() < 0)
		{
			if(!player.isDead())
			{
				player.kill();
			}
		}
		else if(tiles.getTile((int)Math.floor(player.nextPos().getX()), (int)Math.floor(player.nextPos().getY())).isLightWall())
		{
			player.kill();
		}
	}

	public synchronized ClientTileMap getTiles()
	{
		return tiles;
	}

	public synchronized Color getColor(String name)
	{
		return colors.get(name);
	}

	public synchronized void setTile(int x, int y, Color color)
	{
		tiles.setTile(x,y, color);
	}

	public synchronized void setPlayer(String name, Player player)
	{

	}

	public synchronized void setPlayerStatus(String name, double x, double y, boolean isJetWall)
	{
		Player player = players.get(name);
		player.setPos(new Vector2d(x, y));
		player.setLightWall(isJetWall);
		players.put(name, player);
	}
}
