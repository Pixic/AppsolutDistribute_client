package weather_server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class defines client connection behaviour for display clients.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class DisplayClientSocketReceiver extends ClientSocketReceiver {

	public DisplayClientSocketReceiver(Server server,ServerSocket serverSocket) {
		super(server,serverSocket);
	}
	
	protected void createClientHandler(Server server,Socket clientSocket) {
		new DisplayClientHandler(server,clientSocket);
	}
}
