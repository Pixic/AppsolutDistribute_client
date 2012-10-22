package components; 

import componentinterface.Command;  

public class Stop implements Command {
	public void execute() {
		System.out.println("stop");
	}
}
