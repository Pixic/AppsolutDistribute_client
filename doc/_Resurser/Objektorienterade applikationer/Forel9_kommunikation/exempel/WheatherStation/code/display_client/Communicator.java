package display_client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Establishes a client server socket connection to the weather server.
 * Sends update requests to the server, 
 * and receives current temperature data from the server.
  * @author  Uno Holmer
 * @version 2008-02-07
 */
public class Communicator {
	private Socket socket;
	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;

	public Communicator(String address,int port) {
		connect(address,port);
	}

	/** Establishes a client server socket connection to the weather server.
	 * @param address the server address
	 * @param port the server port for update requests.
	 */
	private void connect(String address,int port) {
		InetAddress toAddr = null;
		try {
			toAddr = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		try {
			socket = new Socket(toAddr,port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
			inStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Sends update requests to the weather server.
	 * Receives current temperature data from the server.
	 * @return the temperature data in textual tabular form. 
	 */
	public String requestUpdate() {
		String result = null;
		try {
			outStream.writeObject("update");	// request fresh data
			try {
				result = (String)inStream.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} 
		catch (SocketException e) {
			System.out.println(e.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

