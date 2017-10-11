package com.gray.main;

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
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                System.out.println("UP");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
