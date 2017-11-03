package com.gray.lightcycles.server.scores;

public class Score implements Comparable<Score>
{
	private String name;
	private int score;

	public Score(String name, int score)
	{
		this.name = name;
		this.score = score;
	}

	public String toString()
	{
		return name + "," + score;
	}

	public int compareTo(Score other)
	{
		return this.score - other.getScore();
	}

	public String getName()
	{
		return name;
	}

	public int getScore()
	{
		return score;
	}
}
