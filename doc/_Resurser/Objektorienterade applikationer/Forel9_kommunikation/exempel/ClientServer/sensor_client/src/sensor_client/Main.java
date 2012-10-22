package sensor_client;

import javax.swing.JOptionPane;

/**
 * The sensor client main class.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class Main {
	
	/**
	 * Displays an input dialog which asks the user for the name of the sensor location.
	 * Creates the user interface and the data sender.
	 * @param args
	 */
	public static void main(String[] args) {
		String location = 
		JOptionPane.showInputDialog(
			null,
			"Input sensor location",
			"Weather sensor client",
			JOptionPane.PLAIN_MESSAGE);
		
		String serverAddress = args[0];
		int serverPort = Integer.parseInt(args[1]);
		
		new UserInterface(new DataSender(location,serverAddress,serverPort));
	}
}