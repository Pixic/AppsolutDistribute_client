import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	
	public Main() {
		setTitle("ActionListener2");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton plingButton = new JButton("pling");
		plingButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pling();
					}});
		getContentPane().add(plingButton);
		
		JButton plongButton = new JButton("plong");
		plongButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						plong();
					}});		
		
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

	public static void main(String[] args) {
		new Main();
	}

}
