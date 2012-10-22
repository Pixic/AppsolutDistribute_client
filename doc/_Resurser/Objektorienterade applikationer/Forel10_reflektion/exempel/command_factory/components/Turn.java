package components;

import componentinterface.Command;   

public class Turn implements Command {
	public void execute() {
		System.out.println("turn");
	}
}
