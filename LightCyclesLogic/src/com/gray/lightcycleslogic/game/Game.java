package com.gray.lightcycleslogic.game;

import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.math.Vector2d;

import java.util.HashMap;

public class Game
{
	public static final float VEL = 1;
	HashMap<String, Player> players;
	int playerIndex;

	public Game()
	{
		players = new HashMap<>();
		playerIndex = 1;
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

	public synchronized void setPlayerStatus(String name, float x, float y, boolean isLightWall)
	{
		Player player = players.get(name);
		player.setPos(new Vector2d(x, y));
		player.setLightWall(isLightWall);
		players.put(name, player);
	}
}
