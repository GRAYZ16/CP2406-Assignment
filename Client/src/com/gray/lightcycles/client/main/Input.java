package com.gray.lightcycles.client.main;

import com.gray.lightcycles.logic.entity.Player;
import com.gray.lightcycles.logic.math.Vector2d;
import com.gray.lightcycles.client.net.ServerIOThread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener
{

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
    	Vector2d lastPos;

        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:

                if(Main.player.getVel().getX() != 0.8)
                {
                    (new Thread(new ServerIOThread("USER " + Main.userName + " GO FASTER"))).start();
                    Main.player.setVel(new Vector2d(0.8, 0.8));
                }
				break;
            case KeyEvent.VK_DOWN:

                if(Main.player.getVel().getX() != 0.3)
                {
                    (new Thread(new ServerIOThread("USER " + Main.userName + " GO SLOWER"))).start();
                    Main.player.setVel(new Vector2d(0.3, 0.3));
                }
                break;
            case KeyEvent.VK_LEFT:
                Main.player.turn(Player.LEFT);
                (new Thread(new ServerIOThread("USER " + Main.userName + " TURN LEFT"))).start();
                break;
            case KeyEvent.VK_RIGHT:
                Main.player.turn(Player.RIGHT);
                (new Thread(new ServerIOThread("USER " + Main.userName + " TURN RIGHT"))).start();
                break;
			case KeyEvent.VK_SPACE:
				Main.player.setLightWall(!Main.player.isLightWall());

				if(Main.player.isLightWall())
				{
					(new Thread(new ServerIOThread("USER " + Main.userName + " JETWALL ON"))).start();
				}
				else
				{
					(new Thread(new ServerIOThread("USER " + Main.userName + " JETWALL OFF"))).start();
				}
				break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
				Main.player.setVel(new Vector2d(0.5,0.5));
                (new Thread(new ServerIOThread("USER " + Main.userName + " GO SLOWER"))).start();
                break;
            case KeyEvent.VK_DOWN:
				Main.player.setVel(new Vector2d(0.5,0.5));
                (new Thread(new ServerIOThread("USER " + Main.userName + " GO FASTER"))).start();
                break;
        }
    }
}
