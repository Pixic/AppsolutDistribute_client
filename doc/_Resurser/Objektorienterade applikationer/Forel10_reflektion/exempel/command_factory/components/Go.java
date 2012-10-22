package components;

import componentinterface.Command;  

public class Go implements Command {
	public void execute() {
		System.out.println("go");
	}
}
