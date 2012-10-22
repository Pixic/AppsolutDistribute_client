package components; 
 
import componentinterface.Command;  

public class Take implements Command {
	public void execute() {
		System.out.println("take");
	}
}
