package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

/**
 * Simple handler for checking incoming passwords against passwords stored in a file. 
 * @author Andreas Nilsson and Morgan Eklund
 */
public class UserHandler extends Observable {
	
	private HashMap<String, String> userlist;
	private String filename;
	
	/**
	 * Constructs a new UserHandler.
	 */
	public UserHandler() {
		userlist = new HashMap<String, String>();
		filename = "userlist.dat";
	}
	
	/**
	 * Writes all usernames and password pairs to file.
	 */
	private void save() {
		
		try {
			
			FileOutputStream out = new FileOutputStream(filename);
			Iterator<String> it = userlist.keySet().iterator();
			while(it.hasNext()) {
				String username = it.next();
				for (int i=0; i < username.length(); i++)
					out.write(username.charAt(i));
				out.write(0);
				String password = userlist.get(username);
				for (int i=0; i < password.length(); i++)
					out.write(password.charAt(i));
				out.write(0);
			}
			out.close();
			
		} catch (IOException e) {
			setChanged();
			notifyObservers(e);
		}
		
	}
	
	/**
	 * Initializes username and password pair list.
	 */
	public void load() {
		
		FileInputStream in = null;
		try {
			
			if (!new File(filename).exists())
				new File(filename).createNewFile();
			
			int tmp = 0;
			in = new FileInputStream(filename);
			
			while (tmp != -1) {
				
				String user = "";
				while ((tmp = in.read()) != 0 && tmp != -1)
					user += (char)tmp;
				
				String pass = "";
				while ((tmp = in.read()) != 0 && tmp != -1)
					pass += (char)tmp;
				
				if (tmp != -1)
					userlist.put(user, pass);
				
			}
			
		} catch (EOFException e) {
			// End-Of-File - ignore
		} catch (IOException e) {
			setChanged();
			notifyObservers(e);
		}
		
		try {
			in.close();
		} catch (IOException e) {
			setChanged();
			notifyObservers(e);
		}
	}
	
	/**
	 * Checks if <code>username</code> and <code>password</code> matches.<br>
	 * If <code>username</code> is not currently in use, the pair is added to the valid list.
	 * @param ip
	 * @param username
	 * @param password
	 * @return <code>true</code> only if username and password pair is a match or if username is not in use, <code>false</code> otherwise.
	 */
	public boolean auth(String username, String password)  throws IOException {
		
		if (userlist.containsKey(username))
			return userlist.get(username).equals(password);
		else  {
			userlist.put(username, password);
			save();
			return true;
		}
		
	}
	
	/**
	 * Sets the filename.
	 * @param filename
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
