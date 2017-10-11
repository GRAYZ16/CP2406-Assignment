package com.gray.net;

import com.gray.main.Main;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerIOThread implements Runnable
{
    String msg;
    public ServerIOThread(String msg)
    {
        this.msg = msg;
    }

    @Override
    public void run()
    {
        ClientNetwork.network.sendPacket(ClientNetwork.serverAddress, ClientNetwork.serverPort, msg);

        String command = msg.split(" ")[0];

        if(command.equals("ADD") || command.equals("REMOVE") || command.equals("GRID") || command.equals("SAVE") || command.equals("GET"))
            receiveResponse(command);

    }

    public void receiveResponse(String command)
    {
        DatagramPacket packet = ClientNetwork.network.receivePacket();
        String payload = new String(packet.getData(), packet.getOffset(), packet.getLength());

        switch(command)
        {
            case "ADD":
                if(payload.equals("OKAY"))
                    return;
                else
                    System.out.println("ERROR");
                break;
            case "REMOVE":
                if(payload.equals("OKAY"))
                    return;
                else
                    System.out.println("ERROR");
                break;
            case "GRID":
                String[] gridSize = payload.split(" ");
                Main.windowSize.set(Double.parseDouble(gridSize[0]), Double.parseDouble(gridSize[1]));
                break;
            case "SAVE":
                if(payload.equals("OKAY"))
                    return;
                else
                    System.out.println("ERROR");
                break;
            case "GET":
                //TODO: Implement Leaderboards
                break;
        }
    }
}
