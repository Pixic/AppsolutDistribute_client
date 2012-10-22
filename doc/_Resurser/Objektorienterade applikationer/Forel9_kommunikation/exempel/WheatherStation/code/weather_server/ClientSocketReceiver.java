package weather_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class handles the reception of client connection attempts.
 * For each new connection request, a thread is spawned to handle the client henceforth. 
 * The client specific behaviour is supposed to be defined by subclassing this class.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public abstract class ClientSocketReceiver extends Thread {
	protected Server server;
	private ServerSocket serverSocket; 
	
	public ClientSocketReceiver(Server server, ServerSocket serverSocket) {
		this.server = server;
		this.serverSocket = serverSocket;
		start();
	}

    public void run() {
    	while (true) {
    		try {
				Socket clientSocket = serverSocket.accept();
			    createClientHandler(server,clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    /** Creates a client handler thread object.
     * @param server this server reference is passed on to the client handler. 
     * @param clientSocket the socket used in the communication with the client
     */
    protected abstract void createClientHandler(Server server,Socket clientSocket);
}
