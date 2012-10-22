package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Observable;

/**
 * Simple Logger.
 * @version 2012-02-20
 * @author Andreas Nilsson and Morgan Eklund
 */
public class Logger extends Observable {
	
	private boolean active;
	
	/**
	 * Constructs a new Logger.
	 */
	public Logger() {
		active = true;
	}
	
	/**
	 * Appends the text <code>msg</code> to the end of logging file.<br>
	 * Filename: Log-[yyyy-MM-dd].txt where yyyy is year, MM is month and dd is day in month. 
	 * @param msg Text to append.
	 */
	public void append(String msg) {
		if (isActive()) {
			Writer output;
			try {		
				output = new BufferedWriter(new FileWriter("Log-" + getTime("yyyy-MM-dd") + ".txt", true));
				output.write(getTime("yyyy-MM-dd HH:mm:ss") + " " + msg + System.getProperty("line.separator"));
				output.close();
			} catch (IOException e) {
				// Not able to write to file
				setChanged();
				notifyObservers(e);
			}
		}
	}
	
	/**
	 * Gets current time formatted.
	 * @param format SimpleDateFormat
	 * @return String representation of time in the specified format.
	 */
	private String getTime(String format) {
		return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
	}
	
	/**
	 * Sets active state, default <code>true</code> .
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
	/**
	 * Checks if active, default <code>true</code>.
	 * @return <code>true</code> if logger is active, <code>false</code> otherwise.
	 */
	public boolean isActive() {
		return active;
	}
}
