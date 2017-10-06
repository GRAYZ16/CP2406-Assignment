import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClientTest
{
	public static void main(String[] args) throws Exception
	{
		int mcPort = 25535;
		String mcIPStr = "230.1.1.1";

		MulticastSocket mcSocket;
		InetAddress mcIPAddress;

		mcIPAddress = InetAddress.getByName(mcIPStr);
		mcSocket = new MulticastSocket(mcPort);
		System.out.println("Server at: " + mcSocket.getLocalSocketAddress());
		mcSocket.joinGroup(mcIPAddress);

		while(true)
		{
			DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

			System.out.println("Waiting for msg...");
			mcSocket.receive(packet);

			String msg = new String(packet.getData(), packet.getOffset(), packet.getLength());

			System.out.println("Received: " + msg);
		}

		/*String command = "USER Josh TURN";

		DatagramPacket send = new DatagramPacket(command.getBytes(), command.length());
		send.setPort(packet.getPort());
		send.setAddress(packet.getAddress());
		mcSocket.send(send);*/

		//mcSocket.leaveGroup(mcIPAddress);
		//mcSocket.close();
	}
}