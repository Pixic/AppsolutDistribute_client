import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;



//Handles the connection between the server and the specific client
public class ClientConnection implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String[] response = {"Tre �nder sitter stilla vid �kanten....de har andakt.",
			"Varf�r f�r inte jag rida n�r Ullared?",
			"- Hur m�nga indianer f�r plats i ett tr�d? - Hela stammen!",
			"Den dumma fisken imbisill.",
			"- Vad kallas en nyanst�lld p� McDonalds?- Nyburgare."};
	
	private Random random;

	public ClientConnection(Socket client)
	{
		socket = client;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		random = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//create I/O reader etc.
		
		while(true)
		{
			
			//Do work here
			String readLine = null;
			try {
				readLine = in.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(readLine != null) {
				System.out.println("Message: " + readLine);
				out.println(response[random.nextInt(4)]);
			}
		}
		
	}
	
}
