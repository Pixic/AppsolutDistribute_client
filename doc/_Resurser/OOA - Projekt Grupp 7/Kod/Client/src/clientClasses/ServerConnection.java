package clientClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Observer;

/**
 * This is a class that handles the socket to the server the client is connected to.
 * 
 * @author Robin Braaf, Fredrik Hansson
 * @version 2012-02-22
 */

public class ServerConnection extends Socket{

	PrintWriter out;
	ServerListener serverListener;
	Thread t;
	
	/**
	 * Creates the ServeConnection object with all connections set to null.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ServerConnection(String host, int port) throws UnknownHostException, IOException{
		super(host, port);
	}
	
	/**
	 * Try to login on the server with specified nickname and password.
	 * This method encrypts the password with MD5 encryption before trying 
	 * to login.
	 * @param password The password connected with the user name.
	 * @param user The nickname you want to login with.
	 * @return "OK" if authentication was accepted.
	 * @throws IOException If anything went wrong with the in or out streams.
	 */
	public String auth(String user, String password) throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(this.getInputStream()));
		out = new PrintWriter(this.getOutputStream(), true);
		String serverResponse;
		if((user = checkNick(user)).equals("FALSE")){
			return "Invalid username. Username can't contain \":\"";
		}
		//MD5 ENCYPTION
		byte[] passArray = password.getBytes();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(passArray);
			byte[] passDigest = md.digest();
			
			password = new String(passDigest);
			
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		out.println("NICK:" + user + ":" + password);
		//Check response from server
		serverResponse = in.readLine();
		//Test if server response is equal to authentication successful
		if(!(serverResponse.equals("CMD_AUTH_OK"))){ 
			return serverResponse;
		}
		serverListener = new ServerListener(this);
		t = new Thread(serverListener);
		t.start();
		return "OK";
	}
	
	//Check if specified user name is accepted
	private String checkNick(String user){
		user.trim();
		if(user.contains(":")){
				return "FALSE";
		}
		return user;
	}
	
	/**
	 * Close the socket and stop the serverListener.
	 */
	public void disconnect(Observer o){
		//Release resources connected to server.
		t.interrupt();
		serverListener.deleteObserver(o);
		serverListener = null;
		out.close();	
		
		try {
			this.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends a message to all people in the chat room. If the String is longer than
	 * 256 chars it will be cut off to the 256 first chars and the rest will be discarded.
	 * @param msg Broadcast a message to the chat room. String can not be longer than 256 chars.
	 */
	public void sendMessage(String msg){
		if(!(msg.equals(""))){
			if(msg.length() >= 256){
			msg = msg.substring(0, 255);
			}
			out.println("BC:" + msg);
		}
	}
	
	/**
	 * Sends a private message to a user connected on the server. If the String is longer than
	 * 256 chars it will be cut off to the 256 first chars and the rest will be discarded.
	 * @param to Receiver of the message String can not be longer than 256 chars.
	 * @param msg message to the receiver. String can not be longer than 256 chars.
	 */
	public void sendPM(String to, String msg){
		if(!(msg.equals(""))){
			if(msg.length() >= 256){
				msg = msg.substring(0, 255);
			}
			if(to.length() >= 256){
				to = to.substring(0, 255);
			}
		out.println("MSG:" + to + ":" + msg);
		}
	}
	
	/**
	 * Gets the serverListener which is listening on this server.
	 */
	public ServerListener getServerListener(){
		return serverListener;
	}
}