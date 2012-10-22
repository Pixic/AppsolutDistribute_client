package clientClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;

/**
 * A class that listens to incoming message from the server and 
 * notifies observers with the received message.
 * @author Robin Braaf, Fredrik Hansson
 * @version 2012-02-10
 */
public class ServerListener extends Observable implements Runnable {
	private BufferedReader in = null;
	
	/**
	 * 
	 * @param server The socket connected with the server.
	 * @throws IOException 
	 */
	
	public ServerListener(ServerConnection server) throws IOException{
		in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
	}
	
	/**
	 * 
	 */
	@Override
	public void run() {
		try {
			String msg;
			while ((msg = in.readLine()) != null) {
				setChanged();
				notifyObservers(msg);
			}
			in.close();
			

		} catch (IOException e) {
			//TODO
			//System.err.println(e);
		}
		setChanged();
		notifyObservers("DC");
		
	}
	
	/**
	 * Closes the stream to the server.
	 */
	public void setStop(){
		try{
			in.close();
		}catch(IOException e){}
	}
	
	/**
	 * Do not use while running.
	 * @param server the socket connected to the server. The ServerListener will listen
	 * to messages from this socket.
	 */
	public void setStream(ServerConnection server) throws IOException{
		in = new BufferedReader(new InputStreamReader(
				server.getInputStream()));
	}
}
