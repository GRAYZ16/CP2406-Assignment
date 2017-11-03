package com.gray.scores;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Scoreboard
{
	public static void newScore(String player, int score)
	{
		try
		{
			List<String> scores = Files.readAllLines(Paths.get("scores.txt"), Charset.forName("UTF-8"));
			scores.add(player + "," + score);
			Files.write(Paths.get("scores.txt"), scores, Charset.forName("UTF-8"));


		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		newScore("Evie", 500);
	}
}
