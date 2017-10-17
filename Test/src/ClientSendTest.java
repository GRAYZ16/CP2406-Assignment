import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Scanner;


import com.gray.lightcycles.logic.net.Network;

public class ClientSendTest
{
	public static void main(String[] args) throws IOException
	{

		Network network = new Network("230.1.1.1", 25535);
		String serverIP;
		int serverPort;

		while(true)
		{
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

			System.out.println("Waiting for msg...");
			packet = network.receiveBroadcast();

			String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());

			String[] recv = msg.split(",");
			if(recv.length == 2)
			{
				serverIP = recv[0];
				serverPort = Integer.parseInt(recv[1]);
				break;
			}
		}

		String command;

		while(true)
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter a command > ");
			command = scanner.nextLine().trim();

			network.sendPacket(serverIP, serverPort, command);
			DatagramPacket packet = network.receivePacket();
			String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());
			System.out.println(msg);
		}
	}
}
