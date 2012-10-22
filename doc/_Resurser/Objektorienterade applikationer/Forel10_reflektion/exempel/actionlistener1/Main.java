import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
	
	public Main() {
		setTitle("ActionListener");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton plingButton = new JButton("pling");
		plingButton.addActionListener(this);
		getContentPane().add(plingButton);
		
		JButton plongButton = new JButton("plong");
		plongButton.addActionListener(this);
		getContentPane().add(plongButton);
		
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

	/*
	 *  Traditional event handling using a switch statement 
	 */
	public void actionPerformed(ActionEvent e) {
		if ( e.getActionCommand().equals("pling"))
			pling();
		else if ( e.getActionCommand().equals("plong"))
			plong();
		else 
			System.out.println("Unknown command");
	}

	public static void main(String arg[]) {
		new Main();
	}
}
