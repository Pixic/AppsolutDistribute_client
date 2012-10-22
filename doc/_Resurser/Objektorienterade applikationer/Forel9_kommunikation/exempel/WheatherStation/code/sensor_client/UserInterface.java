package sensor_client;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Defines the user interface for sensor clients.
 * The GUI contains a simple text field.
 * When entering a temperature value and pressing the enter key
 * the value is passed on to a data sender object which sends the value
 * to the server.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class UserInterface {
	private DataSender dataSender;
	private JFrame frame;
	private JTextField inputField;
	
	public UserInterface(DataSender dataSender) {
		this.dataSender = dataSender;
		makeFrame();
	}

	private void makeFrame() {
		frame = new JFrame("Sensor client for " + dataSender.getLocation());
		frame.setLayout(new FlowLayout());
		Container contentPane = frame.getContentPane();	
		contentPane.add(new JLabel("Send temperature to server"));
		inputField = new JTextField(10);
		inputField.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e)  {
						dataSender.sendData(Float.parseFloat(inputField.getText()));
						inputField.setText("");
					}
				}
		);
		contentPane.add(inputField);
					
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
