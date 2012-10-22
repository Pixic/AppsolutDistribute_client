package weather_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import weatherlib.*;

/**
 * This class defines client handler behaviour for sensor clients.
 * The communication is carried out via object streams.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class SensorClientHandler extends ClientHandler {
	private ObjectInputStream in = null;
	
	public SensorClientHandler(Server server,Socket clientSocket) {
		super(server,clientSocket);
		start();
	}

	public void run() {
		try {
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		handleRequests();
	}
	
	/**
	 * Receives temperature observations from a single sensor client,
	 * and passes the values to the server for storage in the database.
	 */
	private void handleRequests() {
		TemperatureObservation observation;
		while (true) {
		    try {
		    	observation = (TemperatureObservation)in.readObject();
			} catch (Exception e) {
				try {
					in.close();
					clientSocket.close();
				} catch (IOException e1) { }
				return;
			}
            server.update(observation);
		}	
	}
}
