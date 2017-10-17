package com.gray.lightcycles.client.state;

public class StateManager
{
	public static final int MAIN_STATE = 1;
	public static final int GAME_STATE = 0;

	private int state;

	public StateManager(int startState)
	{
		state = startState;
	}

	public void changeState(int state)
	{
		this.state = state;
	}
}
