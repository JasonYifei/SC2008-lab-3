import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Rfc865UdpServer {
	public static void main(String[] argv) {
		// 1. Open UDP Socket at well-known port
		System.out.println("Server Running..!");
		DatagramSocket socket = null;
		byte[] buffer = new byte[512];
		
		try {
			//socket = new DatagramSocket();
			socket = new DatagramSocket(1234); // only listening on port 1234
		} catch (SocketException e) {
			
			System.out.print(e);
		}
		
		while (true) {
			try {
				// 2. Listen for UDP Request from Client
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				socket.receive(request);
				String received = new String(request.getData(), 0, request.getLength());
				System.out.println(received);
				InetAddress address = request.getAddress();
				int port = request.getPort();
				
				// 3. Send UDP Reply back to Client		
				String replyMessage = new String("Quote of the day: something inspirational change ");
				buffer = replyMessage.getBytes();
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length, address, port);
				socket.send(reply);
				
			} catch (IOException e) {}
		}
	}
}