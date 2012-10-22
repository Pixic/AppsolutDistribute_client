package model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

/**
 * Simple TCP Server.
 * 
 * @version 2012-02-30
 * @author Andreas Nilsson and Morgan Eklund
 */
public class Server extends Observable implements Observer, Runnable {
	
	public static int MAX_MSG_LENGTH = 300;
	
	private ServerSocket socket;
	private int port;
	private ChatServerProtocol protocol;
	private boolean listening;
	private Thread thread;
	private Logger logger;
	private UserHandler handler;
	
	/**
	 * Constructs a new Server and sets default listening port (1234).
	 */
	public Server() {
		port = 1234;
		listening = false;
		handler = new UserHandler();
		handler.addObserver(this);
		protocol = new ChatServerProtocol(handler);
		protocol.addObserver(this);
		logger = new Logger();
		logger.addObserver(this);
	}
	
	/**
	 * Starts the server.<br>
	 * Starts listening after new clients.
	 */
	public void start() {
		if (!listening) {
			logger.append("SERVER STARTED");
			handler.load();
			thread = new Thread(this);
			thread.start();
		}
	}
	
	/**
	 * Stops the server and disconnects all connected clients.
	 * @throws IOException
	 */
	public void stop() throws IOException {
		thread.interrupt();
		socket.close();
		protocol.disconnectUsers();
		listening = false;
		logger.append("SERVER STOPPED");
	}
	
	@Override
	public void run() {
		
		try {
			socket = new ServerSocket(port);
			listening = true;
		} catch (IOException e) {
			setChanged();
			notifyObservers(e);
			logger.append("SERVER ERROR:\t" + "Not able to listen on port: " + getPort());
			return;
		}
		logger.append("SERVER RUNNING\t" + getIP() + "\t" + getPort());
		
		Socket client = null;
        while(true) {
            try {
                client = socket.accept();
                new Thread(new ClientConnection(client, protocol)).start();
            } catch (SocketException e) {
            	if (e.getMessage().equals("Socket is closed"))
            		break;			// server.stop() is executed, let thread terminate.
            } catch (IOException e) {
            	logger.append("CLIENT FAILED TO CONNECT");
                setChanged();
                notifyObservers(e); // Notify GUI: Client failed to connect
            }
        }
	}
	
	@Override
	public void update(Observable o, Object obj) {
		
		if (o instanceof ChatServerProtocol && obj instanceof String) {
			logger.append(((String)obj).replace(":", "\t\t"));
			obj = "PROTOCOL:" + (String)obj;
		}
		else if (o instanceof Logger && obj instanceof IOException)
			obj = "LOGGER:" + ((IOException) obj).getMessage();
		else if (o instanceof UserHandler && obj instanceof IOException)
			obj = "USERHANDLER:" + ((IOException) obj).getMessage();
		
		setChanged();
		notifyObservers(obj);
	}
	
	/**
	 * Ban this IP address.
	 * @param ip
	 */
	public void banIP(String ip) {
		protocol.banIP(ip);
	}
	
	/**
	 * Unban this IP address.
	 * @param ip
	 */
	public void unbanIP(String ip) {
		protocol.unbanIP(ip);
	}
	
	/**
	 * Sets the port number for listening, default 1234.<br>
	 * @param port New port number
	 */
	public void setPort(int port) {
		if (!listening)
			this.port = port;
	}
	
	/**
	 * Gets the current port number for listening, default 1234.
	 * @return Port
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Gets the IP address of the server.
	 * @return IP address
	 */
	public String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return null;
		}
	}
	
	/**
	 * Checks if the server is listening for new clients.
	 * @return <code>true</code> only if server is running, <code>false</code> otherwise.
	 */
	public boolean isRunning() {
		return listening;
	}	
}
