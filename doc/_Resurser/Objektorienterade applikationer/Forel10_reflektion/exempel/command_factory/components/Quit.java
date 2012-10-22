package components;

import componentinterface.Command;   

public class Quit implements Command {
	public void execute() {
		System.out.println("quit");
            System.exit(0);
	}
}
