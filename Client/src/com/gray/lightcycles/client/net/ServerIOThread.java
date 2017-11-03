package com.gray.lightcycles.client.net;

import com.gray.lightcycles.client.main.Main;

import java.net.DatagramPacket;

public class ServerIOThread implements Runnable
{
    private String msg;

    //Thread that handles direct communication between client and server
    public ServerIOThread(String msg)
    {
        this.msg = msg;
    }

    @Override
    public void run()
    {
        ClientNetwork.network.sendPacket(ClientNetwork.serverAddress, ClientNetwork.serverPort, msg);

        String command = msg.split(" ")[0];

        if(command.equals("ADD") || command.equals("REMOVE") || command.equals("GRID") || command.equals("SAVE") || command.equals("GET") || command.equals("GAME"))
            receiveResponse(command);

    }

    //For requests that require a response, wait and verify the response
    private void receiveResponse(String command)
    {
        DatagramPacket packet = ClientNetwork.network.receivePacket();
        String payload = new String(packet.getData(), packet.getOffset(), packet.getLength());

        switch(command)
        {
            case "ADD":
                if(payload.equals("OKAY"))
                    return;
                else
                    System.out.println("WAITING TO ADD");
                break;
            case "REMOVE":
                if(payload.equals("OKAY"))
                    return;
                else
                    System.out.println("WAITING TO REMOVE");
                break;
            case "GRID":
                String[] gridSize = payload.split(" ");
                Main.windowSize.set(Double.parseDouble(gridSize[0]), Double.parseDouble(gridSize[1]));
                break;
            case "GAME":
                switch(payload)
                {
					case "IDLE":
						Main.serverState = false;
						break;
					case "WAITING FOR USERS":
						Main.serverState = false;
						break;
					case "PLAYING":
						Main.serverState = true;
						break;
					case "GAME OVER":
						Main.serverState = true;
						break;
                }
                break;
            case "SAVE":
                if(payload.equals("OKAY"))
                    return;
                else
                    System.out.println("ERROR SAVING");
                break;
            case "GET":
            	if(!payload.equals("FAILED"))
				{
					String[] data = payload.split(",");
					for(int i = 0; i < 5; i++)
					{
						System.out.println(data[i + 1]);
					}
				}
                break;
        }
    }
}
