package view;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Is the JDialog for the chatprogram Sheepchat Lets the user enter his
 * username, password and ip-adress.
 * 
 * @author Tommy Kindmark, Cristian Troncoso
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ConnectDialog extends JDialog {

	private JTextField tUsername, tIp;
	private JPasswordField tPassword;
	private JLabel lblUsername, lblPassword, lblIp;
	private JButton bLogin, bCancel;

	private String username, password, ip;
	private boolean state;

	/**
	 * Constructs a new ConnectDialog
	 * 
	 * @param parent
	 */
	public ConnectDialog(Frame parent) {
		super(parent, "Login", true);
		makeFrame();

		pack();
		setResizable(false);
		setLocationRelativeTo(parent);
	}

	private void makeFrame() {

		KeyAdapter connectAdapter = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (e.getSource().equals(tUsername)
							|| e.getSource().equals(tPassword)
							|| e.getSource().equals(tIp)) {
						login();
					}

				}
				super.keyPressed(e);
			}
		};

		state = false;
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints cs = new GridBagConstraints();
		cs.fill = GridBagConstraints.HORIZONTAL;

		lblUsername = new JLabel("Username: ");
		cs.gridx = 0;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(lblUsername, cs);

		tUsername = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 0;
		cs.gridwidth = 1;
		panel.add(tUsername, cs);
		tUsername.addKeyListener(connectAdapter);

		lblPassword = new JLabel("Password: ");
		cs.gridx = 0;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(lblPassword, cs);

		tPassword = new JPasswordField(20);
		cs.gridx = 1;
		cs.gridy = 1;
		cs.gridwidth = 1;
		panel.add(tPassword, cs);
		tPassword.addKeyListener(connectAdapter);

		lblIp = new JLabel("IP-adress: ");
		cs.gridx = 0;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(lblIp, cs);

		tIp = new JTextField(20);
		cs.gridx = 1;
		cs.gridy = 2;
		cs.gridwidth = 1;
		panel.add(tIp, cs);
		tIp.addKeyListener(connectAdapter);

		bLogin = new JButton("Login");

		bLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		bCancel = new JButton("Cancel");

		bCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = false;
				dispose();
			}
		});
		JPanel bp = new JPanel();
		bp.add(bLogin);
		bp.add(bCancel);

		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(bp, BorderLayout.PAGE_END);

	}

	/**
	 * Checks if you have entered valid username, password and IP-adress
	 */
	public void login() {
		if (new String(tPassword.getPassword()).equals("")
				|| tUsername.getText().equals("") || tIp.getText().equals("")
				|| (tUsername.getText().length() > 14)
				|| tUsername.getText().contains(" ")) {
			JOptionPane.showMessageDialog(ConnectDialog.this,
					"Invalid username, password or IP-adress", "Login",
					JOptionPane.ERROR_MESSAGE);
			tUsername.setText("");
			tPassword.setText("");
			tIp.setText("");
		} else {
			username = tUsername.getText().trim();
			password = new String(tPassword.getPassword());
			ip = tIp.getText().trim();
			state = true;
			dispose();
		}
	}

	/**
	 * Returns the username
	 * 
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Returns the password
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Returns the IP-adress
	 * 
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Returns if you are online or offline
	 * 
	 * @return
	 */
	public boolean getState() {
		return state;
	}
}
