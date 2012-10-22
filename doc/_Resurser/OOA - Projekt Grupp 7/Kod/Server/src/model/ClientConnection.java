package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * TCP connection used by Server.
 *  
 * @version 2012-02-20 
 * @author Andreas Nilsson and Morgan Eklund
 */
public class ClientConnection implements Runnable {
	
    private Socket client;
    private ChatServerProtocol protocol;
    private BufferedReader in;
    private PrintWriter out;
    private boolean auth;
    private String nick;

    /**
     * Constructs a new ClientConnection.
     * @param client Socket provided by Server.
     * @param protocol Protocol used by Server.
     */
    ClientConnection(Socket client, ChatServerProtocol protocol) {
        this.client = client;
        this.protocol = protocol;
        this.auth = false;
        try {
        	in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    
    /**
     * Disconnect from Server.
     */
    public void disconnect() {
    	
    	try {
    		client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    /**
     * Send message <code>msg</code> to Server.
     * @param msg Message to send.
     */
    public void sendMessage(String msg) {
    	if (in != null)
    		out.println(msg);
	}
	
	@Override
	public void run() {
		
		String msg;
		String response;
		
		try {
			
			while ((msg = in.readLine()) != null) {
            	
            	if (msg.length() > Server.MAX_MSG_LENGTH)
            		continue;
            	
                response = protocol.process(this, msg);
            	sendMessage(response);
            	
            }
        	
        } catch (NullPointerException e) {
        	// Server has disconnected this client
        } catch (IOException e) {
            // Client has disconnected incorrectly
        }
		
        // Client has disconnected
        if (nick != null)
        	protocol.removeUser(nick);
        
	}
	
	/**
	 * Gets the IP address in string representation.
	 * @return IP address.
	 */
	public String getIP() {
		return client.getInetAddress().getHostAddress();
	}
	
	/**
	 * Checks if client is authenticated.
	 * @return <code>true</code> only if client is authenticated, <code>false</code> otherwise.
	 */
	public boolean isAuthenticated() {
		return auth;
	}
	
	/**
	 * Sets the authentication status.
	 * @param auth
	 */
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	
	/**
	 * Sets the nickname of this client.
	 * @param nick New nickname of this client.
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	/**
	 * Gets the nickname of the client.
	 * @return Nickname of client.
	 */
	public String getNick() {
		return nick;
	}
}
