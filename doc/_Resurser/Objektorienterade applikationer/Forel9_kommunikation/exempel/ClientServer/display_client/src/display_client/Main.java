package display_client;

/**
 * The display client main class.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class Main {
	public static void main(String[] args) {
		String serverAddress = args[0];
		int serverPort = Integer.parseInt(args[1]);

		/**
		 * Creates the user interface and the server communication object.
		 * @param args
		 */
		new UserInterface(new Communicator(serverAddress,serverPort));
	}
}
