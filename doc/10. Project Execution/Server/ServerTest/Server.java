import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {

	private static ServerSocket serverSocket;
	private static int port = 3322;
	

	public Server()
	{

	}



	public static void main(String[] args)
	{
		Socket client;
		//Set up the server:
		try{
			System.out.println("Waiting for client connection");
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		while(true)
		{
			client = null;
			//Accept the connection from a new client
			try{
				client = serverSocket.accept();
				System.out.println(client.getInetAddress() + " connected");
				//Create a new ClientConnection in a new thread and start it
				new Thread(new ClientConnection(client)).start();
			}catch(SocketException e){
				e.printStackTrace();
				//Fix this
			}catch(IOException e){
				e.printStackTrace();
				//And this
			}
		}
	}
}


