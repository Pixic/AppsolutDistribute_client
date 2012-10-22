package application;

import javax.swing.JOptionPane;
import componentinterface.Command;

/*
 * This application demonstrates how reflection can be used to
 * construct a decoupled factory class.
 * This application is completely decoupled from the subclasses
 * of the interface "Command".
 * @author Uno Holmer
 * @version 2009-02-03
 */

public class Main {
    // Read command names and execute the corresponding commands.
    // The command classes are loaded during run-time.
    public Main(String componentsPackage) {
        String cmd;
        while (true) {
            cmd = JOptionPane.showInputDialog(null,"Choose command"); 
            if (cmd == null)
                System.exit(0);
            processCommand(componentsPackage,cmd);
        }
    }
	
    public void processCommand(String componentsPackage,String commandName) {
        commandName = commandName.trim().toLowerCase();
	  // Capitalize the first letter
	  commandName = Character.toUpperCase(commandName.charAt(0)) 
					  + commandName.substring(1);
	  // Prepend the package name:    components.Classname
	  final String qualifiedClassName = 
		    componentsPackage + "." + commandName;
		
	  Command command = getFactoryCommand(qualifiedClassName);
	  if ( command != null )
	      command.execute();
        else
            JOptionPane.showMessageDialog(null,
					commandName,
					"Illegal command",
					JOptionPane.ERROR_MESSAGE
		);
	}
	
	public static Command getFactoryCommand(String nameString) {
	    Class cmdClass;
	    try {
	        // Try to load the class with the given name.
    	        cmdClass = Class.forName(nameString);
           }
           catch (ClassNotFoundException e) {
	         return null;
	     }
	     // Class loading succeeded - avancez!
	     Command command = null;
	     try {
	         // Create an "normal" object from the class object
		   Object cmdObject = cmdClass.newInstance();
		   // Cast it to the Command interface
		   command = (Command)cmdObject;
	     } catch (InstantiationException e) {
	         e.printStackTrace();
	     } catch (IllegalAccessException e) {
	         e.printStackTrace();
	     } 
	     return command;
	 }
	
	 // arg[0] is supposed to be the name of a component package
	 public static void main(String[] arg) {
	     new Main(arg[0]);
	 }
}
