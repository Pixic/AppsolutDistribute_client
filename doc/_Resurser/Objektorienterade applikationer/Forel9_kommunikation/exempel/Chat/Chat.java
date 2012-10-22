package chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.IOException;

class Receiver implements Runnable {
	Thread activity = new Thread( this );
	MulticastSocket socket;
	JTextArea textDisplay;
	
	Receiver( MulticastSocket socket, JTextArea textDisplay ) {
		this.socket = socket;
		this.textDisplay = textDisplay;
		activity.start();
	}	

	public void run() {
		byte[] data = new byte[ 1024 ];
		DatagramPacket packet = new DatagramPacket( data, data.length );
		while ( true ) {
			try {
				socket.receive( packet );
				String message = new String( data, 0, packet.getLength() );
				textDisplay.append( message + '\n' );
			}
			catch ( IOException ie ) {
				break;
			}	
		}
	}
}

public class Chat extends JFrame implements ActionListener {
	JButton quitButton = new JButton( "Disconnect" );
    JTextArea textDisplay = new JTextArea();
    JScrollPane scrollPane = new JScrollPane( textDisplay );
    JTextField textEntryField = new JTextField();
    
    InetAddress iaddr;
    MulticastSocket socket;
    int port;
    String userName;

    public Chat( String userName, String groupAddress, int port ) throws IOException {
    	this.userName = userName;
        iaddr = InetAddress.getByName( groupAddress );
	    this.port = port;
	    socket = new MulticastSocket( port );
	    socket.joinGroup( iaddr );
	    new Receiver( socket, textDisplay );
	    sendMessage( "CONNECTED" );

        // Define window layout
		setTitle( "Chat " + userName );
		textDisplay.setEditable( false );
		getContentPane().add( quitButton, BorderLayout.NORTH );
		getContentPane().add( scrollPane, BorderLayout.CENTER	 );
		getContentPane().add( textEntryField, BorderLayout.SOUTH );
		quitButton.addActionListener( this );
		textEntryField.addActionListener( this );
		setSize( 400, 250 );
		setVisible( true );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
    }

    public void sendMessage(String message) {
    	byte[] data = ( userName + ": " + message ).getBytes();
    	DatagramPacket packet = new DatagramPacket( data, data.length, iaddr, port);
    	try {
    		socket.send(packet);
    	} 
    	catch (IOException ie) {}
    }

    public void actionPerformed( ActionEvent e ) {
    	if ( e.getSource() == textEntryField ) {
    		sendMessage( textEntryField.getText() );
    		textEntryField.setText( "" );
    	} else if ( e.getSource() == quitButton ) {
    		sendMessage( "DISCONNECTED" );
    		try {
    			socket.leaveGroup( iaddr );
    		}
    		catch ( IOException ie ) {}
    		socket.close();
    		dispose();
    		System.exit( 0 );
    	}
    }


    public static void main( String[] arg ) throws IOException {
    	String name = "Anonymous";
    	if ( arg.length > 0 )
    		name = arg[ 0 ];
        new Chat( name, "234.235.236.237", 9876 );
    }
}
