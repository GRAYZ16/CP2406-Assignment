package com.gray.game;

import java.util.ArrayList;
import java.util.List;

public class Game
{
	ArrayList<Player> players;
	int playerIndex;
	public Game()
	{
		players = new ArrayList<Player>();
		playerIndex = 1;
	}

	public void addPlayer(int x, int y)
	{
		players.add(playerIndex, new Player(playerIndex++, x, y,true));
	}

	public List getPlayers()
	{
		return players;
	}

	public void setPlayerStatus(int playerID, int x, int y, boolean isJetWall)
	{
		Player player = players.get(playerID);
		player.setX(x);
		player.setY(y);
		player.isJetWall();
		players.set(playerID, player);
	}
}
