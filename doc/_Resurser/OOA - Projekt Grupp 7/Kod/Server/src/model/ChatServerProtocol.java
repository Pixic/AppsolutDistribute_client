package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;

/**
 * Protocol used by TCP Server.
 * @version 2012-02-20
 * @author Andreas Nilsson and Morgan Eklund
 */
public class ChatServerProtocol extends Observable {

	private static final String AUTH_OK = "CMD:AUTH_OK";
	private static final String AUTH_FAILED = "CMD:AUTH_FAILED";
	//private static final String NICK_IN_USE = "CMD:NICK_IN_USE";
	//private static final String NICK_INVALID = "CMD:NICK_INVALID";
	private static final String REQUEST_OK = "CMD:REQUEST_OK";
	private static final String REQUEST_INVALID = "CMD:REQUEST_INVALID";
	
	private HashMap<String, ClientConnection> connections;
	private HashSet<String> bannedIPs;
	private UserHandler handler;
	
	/**
	 * Constructs a new ChatServerProtocol.
	 * @param handler Handler for authenticating users.
	 */
	public ChatServerProtocol(UserHandler handler) {
		this.handler = handler;
		connections = new HashMap<String, ClientConnection>();
		bannedIPs = new HashSet<String>();
	}
	
	/**
	 * Processes a request <code>msg</code> sent by client <code>conn</code>.
	 * @param conn Connection to client from which the request came.
	 * @param msg Request.
	 * @return "REQUEST_OK" or "AUTH_OK" only if Server has successfully executed the request, otherwise error message. 
	 */
	public String process(ClientConnection conn, String msg) throws IOException {
		
		if (!conn.isAuthenticated()) {
			return authentication(conn, msg);
		}
		
		String[] array = msg.split(":", 2);
		if (array.length < 2)
			return REQUEST_INVALID;
		
		String cmd = array[0];
		String arg = array[1];
		
		if (cmd.equals("BC")) {
			broadcast("BC:" + conn.getNick() + ":" + arg);
			return REQUEST_OK;
		}
		
		if (cmd.equals("MSG")) {
			String[] temp = arg.split(":", 2);
			// temp[0] = receiver username
			// temp[1] = message
			if (temp.length < 2)
				return REQUEST_INVALID;
			if (connections.containsKey(temp[0])) {
				connections.get(temp[0]).sendMessage("MSG:" + conn.getNick() + ":" + temp[1]);
				conn.sendMessage("MSG_SENT:" + temp[0] + ":" + temp[1]);
				return REQUEST_OK;
			} else
				return REQUEST_INVALID;
		}
		
		
		return REQUEST_INVALID;
	}
	
	private String authentication(ClientConnection conn, String msg) throws IOException {
		
		if (bannedIPs.contains(conn.getIP()))
			return AUTH_FAILED;
		
		if (!msg.startsWith("NICK:"))
			return AUTH_FAILED;
		
		String[] auth = msg.substring(5).split(":");
		if (auth.length != 2)
			return AUTH_FAILED;
		
		String username = auth[0];
		String password = auth[1];

		if(!handler.auth(username, password))
			return AUTH_FAILED;
		
		broadcast("JOIN:" + username); // Avoid seeing self-join
		connections.put(username, conn);
		conn.setAuth(true);
		conn.setNick(username);
		conn.sendMessage(AUTH_OK);
		
		// Notify Server
		setChanged();
		notifyObservers("JOIN:" + conn.getIP());
		
		return "USERS:" + getUsers();
	}
	
	private void broadcast(String msg) {
		Iterator<ClientConnection> it = connections.values().iterator();
		while (it.hasNext()) {
			it.next().sendMessage(msg);
		}
	}
	
	private String getUsers() {
		String names = "";
		Iterator<String> it = connections.keySet().iterator();
		while (it.hasNext()) {
			names += it.next() + ":";
		}
		return names;
	}
	
	/**
	 * Disconnects all clients by force.
	 */
	public void disconnectUsers() {
		Iterator<ClientConnection> it = connections.values().iterator();
		while(it.hasNext()) {
			it.next().disconnect();
		}
	}
	
	/**
	 * Disconnects client with username <code>username</code> and notifies all other clients of this disconnect.
	 * @param username Username of the client to be disconnected.
	 */
	public void removeUser(String username) {
		String ip = connections.get(username).getIP();
		connections.remove(username);
		broadcast("LEAVE:" + username);
		
		// Notify Server
		setChanged();
		notifyObservers("LEAVE:" + ip);
	}
	
	/**
	 * Bans the IP address.
	 * @param ip IP address to ban.
	 */
	public void banIP(String ip) {
		bannedIPs.add(ip);
		Iterator<ClientConnection> it = connections.values().iterator();
		while(it.hasNext()) {
			ClientConnection conn = it.next();
			if (conn.getIP().equals(ip))
				conn.disconnect();
		}
	}
	
	/**
	 * Unbans the IP address.
	 * @param ip IP address to unban.
	 */
	public void unbanIP(String ip) {
		bannedIPs.remove(ip);
	}
}
