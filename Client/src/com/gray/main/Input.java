package com.gray.main;

import com.gray.lightcycleslogic.entity.Entity;
import com.gray.lightcycleslogic.entity.Player;
import com.gray.lightcycleslogic.math.Vector2d;
import com.gray.net.ServerIOThread;

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
                (new Thread(new ServerIOThread("ADD"))).start();
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                Main.player.turn(Player.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                Main.player.turn(Player.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
