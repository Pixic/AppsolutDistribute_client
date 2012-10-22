package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;

import clientClasses.Client;

/**
 * Is the clientwindow for the chatprogram Sheepchat
 * 
 * @author Tommy Kindmark, Cristian Troncoso
 * @version 1.0
 */
public class ClientGui implements Observer {
	private final static String VERSION = "Version 1.0";

	private JFrame clientFrame;
	private JTextArea clientList, chatArea, messageArea;
	private JScrollPane messageScroll, chatScroll, clientScroll;
	private JButton sendButton;
	private JLabel messageLabel;
	private JPanel leftPanel, messagePanel, rightPanel, buttonPanel;
	private StatusPanel statusPanel;
	private HashSet<String> userlist;
	private boolean isConnected = false;
	private JMenuItem connectItem, disconnectItem;

	private Client client;

	/**
	 * Constructs a new ClientGui
	 */
	public ClientGui() {

		makeFrame();

		clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		clientFrame.pack();
		clientFrame.setLocationRelativeTo(null);
		clientFrame.setVisible(true);

		client = new Client();
		client.addObserver(this);
	}

	// Makes the frame and all off its components
	private void makeFrame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}

		clientFrame = new JFrame("Client");
		clientFrame.setSize(200, 250);
		clientFrame.setLayout(new BorderLayout(5, 5));

		clientList = new JTextArea(25, 25);
		chatArea = new JTextArea(25, 40);
		messageArea = new JTextArea(4, 40);

		messageLabel = new JLabel("Write Message");
		sendButton = new JButton("Send");
		leftPanel = new JPanel();
		messagePanel = new JPanel();
		rightPanel = new JPanel();
		buttonPanel = new JPanel();
		statusPanel = new StatusPanel(2);

		connectItem = new JMenuItem("Connect");
		disconnectItem = new JMenuItem("Disconnect");

		disconnectItem.setEnabled(false);

		statusPanel.setText(0, VERSION);
		statusPanel.setText(1, "Offline");
		clientFrame.add(statusPanel, BorderLayout.SOUTH);

		makeChatArea();
		makeMessageArea();
		makeClientList();
		makeMenu();
		makeActionCommands();
	}

	// Makes the menu with File and Help
	private void makeMenu() {
		JMenuBar menuBar = new JMenuBar();
		clientFrame.setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		JMenuItem quitItem = new JMenuItem("Quit");

		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem helpItem = new JMenuItem("Help");

		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (new File("manual.pdf").exists())
						Runtime.getRuntime().exec(
								new String[] { "cmd.exe", "/c", "manual.pdf" });
					else
						JOptionPane.showMessageDialog(clientFrame,
								"Could not find manual (manual.pdf)",
								"File not found",
								JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException ex) {
					// ignore
				}
			}
		});

		JMenuItem aboutItem = new JMenuItem("About");

		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAbout();
			}
		});

		fileMenu.add(connectItem);
		fileMenu.add(disconnectItem);
		fileMenu.add(quitItem);
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
	}

	// Creating the chatArea
	private void makeChatArea() {
		leftPanel
				.setBorder(BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
						"Chat"));
		leftPanel.setLayout(new BorderLayout(5, 5));
		chatArea.setBorder(BorderFactory.createLoweredBevelBorder());
		chatArea.setEditable(false);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		chatScroll = new JScrollPane(chatArea);
		leftPanel.add(chatScroll, BorderLayout.CENTER);
	}

	// Creating the messageArea
	private void makeMessageArea() {
		leftPanel.add(messagePanel, BorderLayout.SOUTH);
		messagePanel.setLayout(new BorderLayout(5, 5));
		messagePanel.add(messageLabel);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		messageScroll = new JScrollPane(messageArea);
		messageScroll.setBorder(BorderFactory.createLoweredBevelBorder());
		messagePanel.add(messageScroll, BorderLayout.SOUTH);
		clientFrame.add(leftPanel, BorderLayout.CENTER);
	}

	// Creating the ClientList
	private void makeClientList() {

		rightPanel.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(EtchedBorder.LOWERED),
				"Connected users"));
		rightPanel.setLayout(new BorderLayout(5, 5));
		clientList.setEditable(false);
		clientList.setBorder(BorderFactory.createLoweredBevelBorder());
		clientScroll = new JScrollPane(clientList);
		clientScroll.setBorder(BorderFactory.createLoweredBevelBorder());
		rightPanel.add(clientScroll, BorderLayout.EAST);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(sendButton, BorderLayout.CENTER);
		rightPanel.add(buttonPanel, BorderLayout.SOUTH);
		clientFrame.add(rightPanel, BorderLayout.EAST);
	}

	// Sendbuttons actionevent
	private void makeActionCommands() {
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controll();
			}
		});

		// Controlling the keyevents in the message area.

		messageArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (KeyEvent.VK_ENTER == event.getKeyCode()) {
					if (!(messageArea.getText().equals(""))) {
						controll();
					}
					event.consume();
				}
				if (messageArea.getText().length() >= 255) {
					String s = messageArea.getText().substring(0, 254);
					messageArea.setText(s);
				}
			}
		});

		// Connect to the server
		connectItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});

		// Disconnect from the server

		disconnectItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disconnect();
			}
		});

		clientFrame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				boolean toSmall = false;
				JFrame tmp = (JFrame) e.getSource();
				int newWidth = tmp.getWidth();
				int newHeight = tmp.getHeight();
				if (newWidth < 500) {
					toSmall = true;
					newWidth = 500;
				}
				if (newHeight < 600) {
					toSmall = true;
					newHeight = 600;
				}
				if (toSmall) {
					tmp.setSize(newWidth, newHeight);
					toSmall = false;
				}
			}
		});
	}

	// Connects the user to a server
	private void connect() {
		String username, password, ipAdress;
		ConnectDialog conDialog = new ConnectDialog(clientFrame);
		conDialog.setVisible(true);
		if (conDialog.getState() == true) {
			username = conDialog.getUsername();
			password = conDialog.getPassword();
			ipAdress = conDialog.getIp();

			String response = client.connect(ipAdress, 1234, username, password);
			if (response.equals("OK")) {
				isConnected = true;
				clientFrame.setTitle(username);
				statusPanel.setText(1, "Online");
				connectItem.setEnabled(false);
				disconnectItem.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(clientFrame, "Connection failed");
			}
		}
	}

	// Disconnects the user
	private void disconnect() {
		client.disconnect();
		isConnected = false;
		clientFrame.setTitle("Client");
		statusPanel.setText(1, "Offline");
		disconnectItem.setEnabled(false);
		connectItem.setEnabled(true);
	}

	// Checks if a PM or BC should be sent and to what user.
	private void controll() {
		boolean userOnline = false;

		if (isConnected) {
			String msg = messageArea.getText();
			msg = msg.trim();
			if (msg.startsWith("/")) {
				String[] firstMsgArray = msg.split("/", 2);
				String[] msgArray = firstMsgArray[1].split(" ", 2);

				for (String pmUser : userlist) {
					if (pmUser.equals(msgArray[0])) {
						if (!(msgArray.length == 1)) {
							sendPM(pmUser, msgArray[1]);
							userOnline = true;
						} else {
							JOptionPane.showMessageDialog(clientFrame,
									"Please enter a text to send a PM");
							userOnline = true;
						}
					}
				}

				if (userOnline == false) {
					JOptionPane.showMessageDialog(clientFrame,
							"Could not find user");
				}
			} else {
				sendMessage(msg);
			}

		} else {
			JOptionPane.showMessageDialog(clientFrame,
					"Not connected to a server");
		}
	}

	// Broadcast a message to all users
	private void sendMessage(String msg) {
		client.sendMessage(msg);
		messageArea.setText("");
	}

	// Send a personal message to one user
	private void sendPM(String user, String msg) {
		client.sendPM(user, msg);
		messageArea.setText("");
	}

	// Shows information about the program
	private void showAbout() {
		JOptionPane.showMessageDialog(clientFrame, "ChatClient " + VERSION,
				"About ChatClient", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		new ClientGui();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof String) {

			String msg = (String) arg1;

			if (msg.equals("Disconnected from server")) {
				isConnected = false;
				statusPanel.setText(1, "Offline");
				disconnectItem.setEnabled(false);
				connectItem.setEnabled(true);

			}
			chatArea.append(msg);
			chatArea.append("\n");
			chatArea.setCaretPosition(chatArea.getText().length());
		} else if (arg1 instanceof HashSet) {
			userlist = (HashSet<String>) arg1;
			clientList.setText("");
			for (String user : userlist) {
				clientList.append(user + "\n");
			}
		}
	}
}
