package com.gray.lightcycleslogic.game;

import java.util.HashMap;
import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.math.Vector2d;
import com.gray.lightcycleslogic.world.TileMap;

public class Game
{
	private final float VEL = 5;
	private  HashMap<String, Player> players;
	private TileMap tiles;

	public Game(int boardSize)
	{
		players = new HashMap<>();
		tiles = new TileMap(boardSize);
	}

	public synchronized void addPlayer(int x, int y, String name)
	{
		players.put(name, new Player( new Vector2d(x, y), new Vector2d(VEL, VEL)));
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

		//TODO: Fix
		for(Player player : players.values()) {
			player.update(delta);
			checkDeath(player);
		}
	}

	public synchronized void checkDeath(Player player)
	{
		Vector2d playerPos = player.getPos();


	}

	public synchronized TileMap getTiles()
	{
		return tiles;
	}

	public synchronized void setTile(int x, int y)
	{
		tiles.setTile(x,y);
	}

	public synchronized void setPlayerStatus(String name, float x, float y, boolean isJetWall)
	{
		Player player = players.get(name);
		player.setPos(new Vector2d(x, y));
		//TODO : Jetwall code
		//player.isJetWall();
		players.put(name, player);
	}
}
