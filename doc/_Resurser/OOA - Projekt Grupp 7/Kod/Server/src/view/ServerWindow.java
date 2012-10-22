package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Server;

/**
 * Is the serverwindow for the chatprogram Sheepchat
 * 
 * @author Tommy Kindmark, Cristian Troncoso
 * @version 1.0
 */
public class ServerWindow implements Observer, ActionListener {

	public static void main(String[] args) {
		Server server = new Server();
		new ServerWindow(server);
	}

	private static final String IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private JFrame frame;
	private JButton startButton;
	private JButton stopButton;
	private JButton banButton;
	private JButton unbanButton;
	private JTextField banField, unbanField;
	private JTextArea info, users, bannedUsers;

	private Server server;

	/**
	 * Constructs a serverwindow
	 * 
	 * @param server
	 */
	public ServerWindow(Server server) {

		this.server = server;
		this.server.addObserver(this);

		frame = new JFrame("Server");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 450));

		createGUI();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	private void createGUI() {

		startButton = new JButton("Start");
		startButton.setFocusable(false);
		startButton.setForeground(Color.BLACK);
		startButton.addActionListener(this);

		stopButton = new JButton("Stop");
		stopButton.setFocusable(false);
		stopButton.setForeground(Color.BLACK);
		stopButton.addActionListener(this);

		banButton = new JButton("Ban");
		banButton.setFocusable(false);
		banButton.setForeground(Color.BLACK);
		banButton.addActionListener(this);

		unbanButton = new JButton("Un-ban");
		unbanButton.setFocusable(false);
		unbanButton.setForeground(Color.BLACK);
		unbanButton.addActionListener(this);

		banField = new JTextField();
		banField.setColumns(9);

		unbanField = new JTextField();
		unbanField.setColumns(9);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(startButton);
		buttonPanel.add(stopButton);
		frame.add(buttonPanel, BorderLayout.NORTH);

		info = new JTextArea() {
			private static final long serialVersionUID = -7152979246649324882L;

			@Override
			public void append(String str) {
				super.append(str);
				setCaretPosition(getText().length());
			}
		};
		info.setEditable(false);
		info.setFont(new Font("Courier New", Font.PLAIN, 12));
		info.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel statusLabel = new JLabel("Status:");
		statusLabel.setForeground(Color.BLACK);

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBorder(BorderFactory.createLineBorder(
				new Color(0, 0, 0, 0), 16));
		infoPanel.add(statusLabel, BorderLayout.NORTH);
		infoPanel.add(new JScrollPane(info), BorderLayout.CENTER);
		frame.add(infoPanel, BorderLayout.CENTER);

		JTabbedPane tabbedPane = new JTabbedPane();

		users = new JTextArea();
		users.setEditable(false);
		users.setFont(new Font("Courier New", Font.PLAIN, 12));
		users.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel userLabel = new JLabel("IP-list:");
		statusLabel.setForeground(Color.BLACK);

		JPanel userPanel = new JPanel();
		JPanel userKickPanel = new JPanel();
		userPanel.setLayout(new BorderLayout());
		userPanel.setBorder(BorderFactory.createLineBorder(
				new Color(0, 0, 0, 0), 16));
		userPanel.add(userLabel, BorderLayout.NORTH);

		userKickPanel.add(banButton);
		userKickPanel.add(banField);
		userPanel.add(userKickPanel, BorderLayout.SOUTH);
		userPanel.add(users, BorderLayout.CENTER);

		bannedUsers = new JTextArea();
		bannedUsers.setEditable(false);
		bannedUsers.setFont(new Font("Courier New", Font.PLAIN, 12));
		bannedUsers.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		JLabel bannedLabel = new JLabel("Ban-list:");
		statusLabel.setForeground(Color.BLACK);

		JPanel bannedUserPanel = new JPanel();
		JPanel unbanPanel = new JPanel();
		bannedUserPanel.setLayout(new BorderLayout());
		bannedUserPanel.setBorder(BorderFactory.createLineBorder(new Color(0,
				0, 0, 0), 16));
		bannedUserPanel.add(bannedLabel, BorderLayout.NORTH);

		unbanPanel.add(unbanButton);
		unbanPanel.add(unbanField);
		bannedUserPanel.add(unbanPanel, BorderLayout.SOUTH);
		bannedUserPanel.add(bannedUsers, BorderLayout.CENTER);

		tabbedPane.addTab("IP-list", new JScrollPane(userPanel));
		tabbedPane.addTab("Ban-list", new JScrollPane(bannedUserPanel));

		frame.add(tabbedPane, BorderLayout.EAST);
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Object src = event.getSource();

		if (src.equals(startButton)) {

			if (!server.isRunning()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						server.start();
						while (!server.isRunning())
							Thread.yield();
						info.append(getFormattedTime() + "SERVER STARTED:\t"
								+ server.getIP() + ":" + server.getPort());
					}
				}).start();
			}
		}

		if (src.equals(stopButton)) {

			if (server.isRunning()) {
				try {
					server.stop();
					info.append(getFormattedTime() + "SERVER STOPPED\t");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(frame, e.getMessage(),
							"Could not disconnect server",
							JOptionPane.WARNING_MESSAGE);
					// System.err.println(e);
				}
			}
		}

		if (src.equals(banButton)) {
			if (banField.getText().matches(IP_PATTERN)) {
				server.banIP(banField.getText());
				bannedUsers.append(banField.getText() + "\n");
				banField.setText("");
			}
		}

		if (src.equals(unbanButton)) {
			if (unbanField.getText().matches(IP_PATTERN)) {
				server.unbanIP(unbanField.getText());
				bannedUsers.setText(bannedUsers.getText().replace(
						unbanField.getText() + "\n", ""));
				unbanField.setText("");
			}
		}
	}

	private String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss").format(Calendar
				.getInstance().getTime());
	}

	private String getFormattedTime() {
		return "\n(" + getTime() + ") ";
	}

	@Override
	public void update(Observable observable, Object arg) {
		if (observable instanceof Server && arg instanceof String) {
			final String[] cmd = ((String) arg).split(":", 2);

			if (cmd[0].equals("LOGGER")) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						JOptionPane.showMessageDialog(frame, cmd[1], "Logger",
								JOptionPane.WARNING_MESSAGE);
					}
				});
			} else if (cmd[0].equals("USERHANDLER")) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						JOptionPane.showMessageDialog(frame, cmd[1],
								"Userlist", JOptionPane.WARNING_MESSAGE);
					}
				});
			} else if (cmd[0].equals("PROTOCOL")) {
				if (cmd[1].startsWith("JOIN:"))
					users.append(cmd[1].substring(5) + "\n");
				if (cmd[1].startsWith("LEAVE:"))
					users.setText(users.getText().replaceFirst(
							cmd[1].substring(6) + "\n", ""));
			}

			info.append(getFormattedTime() + cmd[1].replace(":", ":\t\t\t"));
		}
	}
}
