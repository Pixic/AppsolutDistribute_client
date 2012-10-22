package sensor_client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import weatherlib.*;

/**
 * Establishes a client server socket connection to the weather server.
 * Sends temperature values to the server.
  * @author  Uno Holmer
 * @version 2008-02-07
 */
public class DataSender {
	private String location;
	private Socket socket;
	private ObjectOutputStream outStream;
	
	public DataSender(String location,String address,int port) {
		this.location = location;
		connect(address,port);
	}
	
	/** Establishes a client server socket connection to the weather server.
	 * @param address the server address
	 * @param port the server port for temperature reporting.
	 */
	private void connect(String address,int port) {
		InetAddress toAddr = null;
		try {
		    toAddr = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return;
		}
		try {
			socket = new Socket(toAddr,port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public String getLocation() {
		return location;
	}

	/**
	 * Sends temperature values to the server.
	 * Each value is first aggregated with the location name of the sensor.
	 * @param value the temperature value.
	 */
	public void sendData(float value) {
		try {
			outStream.writeObject(new TemperatureObservation(location,value));
		} 
		catch (SocketException e) {
			System.out.println(e.toString());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
