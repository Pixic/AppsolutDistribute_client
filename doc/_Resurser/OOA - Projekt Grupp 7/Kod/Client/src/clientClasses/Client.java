package 	clientClasses;

import textHandler.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

/**
 * Client without any user interface. This class handles communication with a server and will update
 * observers with messages received from server.
 * 
 * If Client notifies observers with an HashSet<String> (use instance of to typecheck in observers update 
 * method) it should be read as a list of users logged i on the server.
 * 
 * If Client notifies observers with a string it is a message received to be displayed as a 
 * chatmessage.
 * 
 * @author Robin Braaf, Fredrik Hansson
 * @version 2012-02-22
 */
public class Client extends Observable implements Observer{
	
	private ServerConnection server;
	private String user;
	private LogHandler logger;
	private boolean logging;
	private HashSet<String> userList;
	
	/**
	 * Creates a new Client, with no connection to any server or any user name specified.
	 */
	public Client(){
		server = null;
		user = null;
		userList = new HashSet<String>();
	}
	
	/**
	 * Connects to a server and tries to login with requested nickname. Returns "OK"
	 * if successfully connected, and the response from server if connection failed.
	 * 
	 * @param host IP address of the host server in String format.
	 * @param port The port we want to connect to on the server.
	 * @param user username we wish to have.
	 * @return The string returned from server, "OK" if it all was successful.
	 */
	public String connect(String host, int port, String user, String password){
		String response;
		try{
			server = new ServerConnection(host, port);
			response = server.auth(user, password);
		}
		//Catch exceptions.
		catch(UnknownHostException ue){
			return "Cannot connect to server.";
		}catch(IOException ioe){
			return "IO Error!";
		}
		
		if(response == "OK"){
			this.user = user;
			server.getServerListener().addObserver(this);
			setChanged();
			notifyObservers("Succesfully connected to server.");
			try{
			logger = new LogHandler(user);
			logging = true;
			}catch(IOException e){
				logging = false;
				setChanged();
				notifyObservers("Automatic chatlogging turned off because of an IO error.");
			}
		}
		return response;
	}
	
	/**
	 * Take information from server and notifies observers about
	 * information received.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof String){
			String msg = (String)arg1;
			if(msg.equals("DC")){
				disconnect();
				return;
			}
			String[] msgArray = msg.split(":", 2);
			//If its user related message
			if(msgArray[0].equals("USERS")){
				//We got a new user list from server so clear our and 
				//replace it with new one.
				userList.clear();
				String[] userArray = msgArray[1].split(":");
				for(String user : userArray){
					userList.add(user);
				}
				setChanged();
				notifyObservers(userList);
			}
			else if(msgArray[0].equals("JOIN")){
				//If user is not in list already, add him and update list
				//for observers
				if(!(userList.contains(msgArray[1]))){
					userList.add(msgArray[1]);
					setChanged();
					notifyObservers(userList);
				}
			}
			else if(msgArray[0].equals("LEAVE")){
				//If user is in list, remove him and update list for observers.
				if(userList.contains(msgArray[1])){
					userList.remove(msgArray[1]);
					setChanged();
					notifyObservers(userList);
				}
			}
			//If it's a message to notify user about
			else if(msgArray[0].equals("BC")){
				setChanged();
				notifyObservers(msgArray[1]);
				if(logging == true){
					try{
						logger.saveMessage(msgArray[1]);
					}catch(IOException e){}
				}
			}
			//if private message
			else if(msgArray[0].equals("MSG")){
				setChanged();
				String[] pm = msgArray[1].split(":", 2);
				notifyObservers("PM from " + pm[0] + ": " + pm[1]);
				if(logging == true){
					try{
						logger.saveMessage("PM from " + pm[0] + ": " + pm[1]);
					}catch(IOException e){}
				}
			}
			//if private message
			else if(msgArray[0].equals("MSG_SENT")){
				setChanged();
				String[] pm = msgArray[1].split(":", 2);
				notifyObservers("Sent to " + pm[0] + ": " + pm[1]);
				if(logging == true){
					try{
						logger.saveMessage("Sent to " + pm[0] + ": " + pm[1]);
					}catch(IOException e){}
				}
			}
			//TODO CMD MESSAGES
		}
		
		if(arg1 == null){
			disconnect();
			System.exit(1);
		}
	}
	
	/**
	 * Returns the user that is logged in from this client.
	 * @return the user logged in on this client. Returns null if not connected.
	 */
	public String getUser(){
		return user;
	}
	
	/**
	 * Sends a message to all people in the chat room. If the String is longer than
	 * 256 chars it will be cut off to the 256 first chars and the rest will be discarded.
	 * @param msg Broadcast a message to the chat room. String can not be longer than 256 chars.
	 */
	public void sendMessage(String msg){
		server.sendMessage(msg);
	}
	
	/**
	 * Sends a private message to a user connected on the server. If the String is longer than
	 * 256 chars it will be cut off to the 256 first chars and the rest will be discarded.
	 * @param to Receiver of the message String can not be longer than 256 chars.
	 * @param msg message to the receiver. String can not be longer than 256 chars.
	 */
	public void sendPM(String to, String msg){
		server.sendPM(to, msg);
	}
	
	/**
	 * Use this method to disconnect from server.
	 */
	public void disconnect(){
		server.disconnect(this);
		user = null;
		server = null;
		userList.clear();
		setChanged();
		notifyObservers("Disconnected from server");
		setChanged();
		notifyObservers(userList);
	}
	
	/**
	 * Enable or disable automatic logging.
	 * @param b if false it disables automatic logging, if true it enables.
	 */
	public void setLogging(Boolean b){
		logging = b;
	}
}
