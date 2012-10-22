import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

/*
 * This code demonstrates how reflection can be used to
 * construct a general action listener.
 * @author Uno Holmer
 * @version 2006-02-21
 */

public class Main extends JFrame implements ActionListener {
	
	public Main() {
		setTitle("ActionListener3");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		
		JButton plingButton = new JButton("pling");
		plingButton.setActionCommand("pling");	
		plingButton.addActionListener(this);
		getContentPane().add(plingButton);
		
		JButton plongButton = new JButton("plong");
		plongButton.setActionCommand("plong");
		plongButton.addActionListener(this);
		getContentPane().add(plongButton);
		
		JButton klonkButton = new JButton("klonk");
		klonkButton.setActionCommand("klonk");
		klonkButton.addActionListener(this);
		getContentPane().add(klonkButton);
					
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void pling() {
	    System.out.println("Pling");
	}
	
	public void plong() {
	    System.out.println("Plong");
	}
	
	public void klonk() {
	    System.out.println("Klonk");
	}
	
	// General listener with reflection
	public void actionPerformed(ActionEvent ev) {
		try {
			this.getClass().
	        getMethod(ev.getActionCommand()).
		    invoke(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Main();
	}
}

