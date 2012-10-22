package weather_server;

import java.net.Socket;

/**
 * This class is a base class for client handlers.
 * A client handler manages the communication with a single client object.
 * The run method should be defined in subclasses to this class.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public abstract class ClientHandler extends Thread {
	protected Socket clientSocket;
	protected Server server;
	
	public ClientHandler(Server server,Socket clientSocket) {
		this.server = server;
		this.clientSocket = clientSocket;
	}
}
