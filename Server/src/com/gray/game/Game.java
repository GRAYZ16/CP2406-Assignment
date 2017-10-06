package com.gray.game;

import java.util.ArrayList;
import java.util.HashMap;
import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.math.Vector2d;

public class Game
{
	public static final float VEL = 1;
	HashMap<String, Player> players;
	int playerIndex;
	public Game()
	{
		players = new HashMap<>();
		playerIndex = 1;

		players.values();
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


	public synchronized void setPlayerStatus(String name, float x, float y, boolean isJetWall)
	{
		Player player = players.get(name);
		player.setPos(new Vector2d(x, y));
		//TODO : Jetwall code
		//player.isJetWall();
		players.put(name, player);
	}
}
