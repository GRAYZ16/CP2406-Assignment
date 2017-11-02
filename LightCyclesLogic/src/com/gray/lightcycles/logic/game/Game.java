package com.gray.lightcycles.logic.game;

import java.util.HashMap;

import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.lightcycles.logic.world.TileMap;

public class Game
{
	private final float VEL = 0.3f;
	private int boardSize;
	private  HashMap<String, Player> players;
	private TileMap tiles;

	public Game(int boardSize)
	{
		players = new HashMap<>();
		tiles = new TileMap(boardSize);
		this.boardSize = boardSize;
	}

	public synchronized void addPlayer(int x, int y, String name)
	{
		int dir = Player.DIR_RIGHT;
		System.out.println(boardSize/2);
		if(x < boardSize / 2)
		{
			dir = Player.DIR_LEFT;
		}
		else if(y < boardSize / 2)
		{
			dir = Player.DIR_UP;
		}

		players.put(name, new Player( new Vector2d(x, y), new Vector2d(VEL, VEL), dir));
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
		for(Player player : players.values())
		{
			Vector2d lastPos = player.getPos();
			player.update(delta);
			checkDeath(player);

			if(!player.isDead())
			{
				if(player.isLightWall())
				{
					setTile((int)Math.round(lastPos.getX()), (int)Math.round(lastPos.getY()));
				}
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

	public synchronized TileMap getTiles()
	{
		return tiles;
	}

	public synchronized void setTile(int x, int y)
	{
		tiles.setTile(x,y);
	}

	public synchronized void setPlayer(String name, Player player)
	{
		players.put(name, player);
	}

	public synchronized void removePlayer(String name)
	{
		players.remove(name);
	}

	public synchronized void setPlayerStatus(String name, double x, double y, boolean isJetWall)
	{
		Player player = players.get(name);
		player.setPos(new Vector2d(x, y));
		player.setLightWall(isJetWall);
		players.put(name, player);
	}
}
