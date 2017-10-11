package com.gray.main;

import com.gray.Util.Util;

import javax.swing.*;

public class GameThread implements Runnable
{
    private boolean isRunning;
    JFrame frame;
    public static int lastFPS;

    public GameThread(JFrame frame)
    {
        isRunning = true;
        this.frame = frame;
    }

    @Override
    public void run()
    {
        //Find the current time at beginning of execution
        long lastTime = System.nanoTime();

        //The desired time per frame in ns to be able to stabilise at FPS_CAP
        final long TARGET_TIME = 1000000000 / Util.FPS_CAP;


        long fpsTime = 0;
        int fpsCounter = 0;

        while(isRunning)
        {
            //Calculate the time taken to update the frame and reset the last frame time
            long currentTime = System.nanoTime();
            long updateTime = currentTime - lastTime;
            lastTime = currentTime;

            //Calculate the time step for physics updating (Player movement etc)
            double delta = updateTime / ((double)TARGET_TIME);

            fpsTime += updateTime;
            fpsCounter++;

            if(fpsTime > 1000000000)
            {
                lastFPS = fpsCounter;
                System.out.println("FPS: " + fpsCounter);
                fpsCounter = 0;
                fpsTime = 0;
            }

            //update physics and redraw
            frame.repaint();
            Main.tiles.update();

            try
            {
                Thread.sleep((lastTime - System.nanoTime() + TARGET_TIME)/1000000);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
