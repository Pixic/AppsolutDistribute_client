package display_client;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Defines the user interface for display clients.
 * The GUI contains a text area and an update button.
 * When pressing the update button, an request for current temperature values
 * is sent to the weather server.
 * The received values are presented in the text area in tabular form.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class UserInterface {
	private JFrame frame;
	private JTextArea textPane;
	private JButton updateButton;
	private Communicator communicator;
	
	public UserInterface(Communicator communicator) {
		this.communicator = communicator;
		makeFrame();
	}

	private void makeFrame() {
		frame = new JFrame("Wheather service");
		frame.setLayout(new FlowLayout());
		Container contentPane = frame.getContentPane();
		textPane = new JTextArea(null,10,60);
		contentPane.add(textPane);
		
		updateButton = new JButton("Update");
		contentPane.add(updateButton);
		updateButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)  {
						textPane.setText(communicator.requestUpdate());
					}
				}
		);
			
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
