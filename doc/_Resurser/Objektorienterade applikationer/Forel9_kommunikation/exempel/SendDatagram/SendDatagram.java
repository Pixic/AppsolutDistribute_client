import java.net.*;
import java.io.*;

class SendDatagram {
	public static void main( String[] arg ) throws
	UnknownHostException, SocketException, IOException 
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		InetAddress toAddr = InetAddress.getByName(arg[ 0 ]);
		int toPort = Integer.parseInt(arg[ 1 ]);
		DatagramSocket socket = new DatagramSocket();
		
		while (true) {
			System.out.print("? "); System.out.flush();
			String message = in.readLine();
			if (message == null) 
				break;
			byte[] data = message.getBytes();
			socket.send(new DatagramPacket(data, data.length, toAddr, toPort));
		}
	}
}
