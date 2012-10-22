import java.net.*;
import java.io.*;

class ReceiveDatagram {
	public static void main(String[] arg) throws
				SocketException, IOException 
	{
		int receivePort = Integer.parseInt(arg[ 0 ]);
		DatagramSocket socket = new DatagramSocket(receivePort);
		byte[] data = new byte[1024];
		
		while (true) {
			DatagramPacket packet = new DatagramPacket(data, data.length);
			socket.receive(packet);
			System.out.println("Message from " + packet.getAddress().getHostName() + ": ");
			String message = new String(packet.getData(), 0, packet.getLength());
			System.out.println(message);
		}
	}
}
