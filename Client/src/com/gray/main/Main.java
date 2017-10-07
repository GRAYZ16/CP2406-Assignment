package com.gray.main;

import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.game.Game;
import com.gray.net.ClientNetwork;

import java.util.Map;

public class Main
{
	public static Game game;

	public static void main(String[] args)
	{
		game = new Game();

		ClientNetwork network = new ClientNetwork();
		network.run();

		while(true)
		{
			for(Map.Entry<String, Player> player: game.getPlayers().entrySet())
			{
				System.out.println("Player: " + player.getKey() + "\nPosition: " + player.getValue().getPos().getX() + ',' + player.getValue().getPos().getY() + "\nisLightWall?: " + player.getValue().isLightWall());
			}
		}
	}

	public void run()
	{

	}
}
