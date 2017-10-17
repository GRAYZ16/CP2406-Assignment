package com.gray.lightcycles.logic.Util;

import java.util.logging.Logger;

public class LightCyclesLogger
{
	private Logger logger;

	public LightCyclesLogger()
	{
		logger = Logger.getLogger("LightCycles");
	}

	public void info(String message)
	{
		logger.info(message);
	}

	public void severe(String message)
	{
		logger.severe(message);
	}

	public void warning(String message)
	{
		logger.warning(message);
	}

}
