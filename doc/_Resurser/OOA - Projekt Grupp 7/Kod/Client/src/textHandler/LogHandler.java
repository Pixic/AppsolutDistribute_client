package textHandler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 
 * @author Robin Braaf, Fredrik Hansson
 * @version 2012-02-15
 */
public class LogHandler {

	private RandomAccessFile file;
	
	/**
	 * Creates a new log with the current date.
	 * @param user The user that is being logged.
	 * @throws IOException
	 */
	public LogHandler(String user) throws IOException{
		
		file = new RandomAccessFile (user + dateStamp() + ".txt", "rw");
		
	}
	
	//Returns a String with current date as "yy-MM-dd"
	private String dateStamp(){
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		return sdf.format(now.getTime());
	}
	/**
	 * Saves a message to a text file (named by date)
	 * @param message The message to be saved.
	 * @throws IOException
	 */
	public void saveMessage (String message) throws IOException{
		message = message + "\n";
		byte[] msg = message.getBytes();
		file.seek(file.length());	
		file.write(msg);
	}	

}
