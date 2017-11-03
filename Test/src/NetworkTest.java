import com.gray.lightcycles.logic.net.Network;
import org.junit.Assert;
import org.junit.Test;


import java.io.IOException;
import java.net.DatagramPacket;

public class NetworkTest {
	@Test
	public void testSendRead()
	{
		//Testing the direct UDP socket
		try {
			Network network = new Network("228.5.6.7", 49153);
			System.out.println("host IP: " + network.getIP() + " on port: " + network.getPort());
			network.sendPacket(network.getIP(), network.getPort(), "testing 1 2 3");
			DatagramPacket packet = network.receivePacket();
			String message = new String(packet.getData(), packet.getOffset(), packet.getLength());
			network.closeSocket();

			Assert.assertEquals("testing 1 2 3", message);

		} catch (IOException exception) {
			Assert.fail(exception.getMessage());
		}
	}

}