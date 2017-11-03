package com.gray.lightcycles.server.scores;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard
{
	public static void newScore(String player, int score)
	{
		try
		{
			List<String> scores = Files.readAllLines(Paths.get("scores.txt"), Charset.forName("UTF-8"));

			ArrayList<Score> values = new ArrayList<Score>();

			for(String line : scores)
			{
				String[] data = line.trim().split(",");
				values.add(new Score(data[0], Integer.parseInt(data[1])));
			}

			values.add(new Score(player, score));

			Collections.sort(values);
			Collections.reverse(values);

			scores = new ArrayList<String>();

			for(int i = 0; i < 5; i++)
			{
				scores.add(values.get(i).toString());
			}

			Files.write(Paths.get("scores.txt"), scores, Charset.forName("UTF-8"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static List<String> getScores()
	{
		List<String> scores = new ArrayList<>();
		try
		{
			scores = Files.readAllLines(Paths.get("scores.txt"), Charset.forName("UTF-8"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return scores;
	}
}
