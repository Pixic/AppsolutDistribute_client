package weather_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class defines client handler behaviour for display clients.
 * The communication is carried out via object streams.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class DisplayClientHandler extends ClientHandler {
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public DisplayClientHandler(Server server,Socket clientSocket) {
		super(server,clientSocket);
		start();
	}

	public void run() {
		try {
			in = new ObjectInputStream(clientSocket.getInputStream());
			out = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		handleRequests();
	}
	
	/**
	 * Receives update requests from a single display client, and sends back the
	 * server database contents to the client.
	 */
	private void handleRequests() {
		String request = null;
		while (true) {
		    try {
		    	request = (String)in.readObject();     // get update request from display client
			} catch (Exception e) {
				try {
					in.close();
					clientSocket.close();
				} catch (IOException e1) { e1.printStackTrace();}
				return;
			}
            if (request.equals("update"))		       // request for update received
				try {
					out.writeObject(server.getAll());  // return fresh data to client
				} catch (IOException e) {
					e.printStackTrace();
				}
			else
				try {
					out.writeObject("Illegal request");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
