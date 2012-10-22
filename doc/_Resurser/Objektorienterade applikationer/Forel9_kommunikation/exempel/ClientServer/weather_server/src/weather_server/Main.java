package weather_server;

public class Main {
	public static void main(String[] args) {
		new Server(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
	}
}
