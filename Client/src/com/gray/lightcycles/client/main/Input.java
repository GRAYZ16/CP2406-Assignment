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
