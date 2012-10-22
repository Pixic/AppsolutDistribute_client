package weather_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import weatherlib.*;

/**
 * This is the main server class for the weather station application.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class Server {
    private TreeMap<String,TimeStampedValue> table;
    private ServerSocket sensorServerSocket, displayServerSocket;
    
    
    /** Creates a new server object
     * @param sensorServerPortthe server receives sensor client sockets on this port
     * @param displayServerPort the server receives display client sockets on this port
     */
    public Server(int sensorServerPort,int displayServerPort) {
    	table = new TreeMap<String,TimeStampedValue>();
    	try {
			sensorServerSocket = new ServerSocket(sensorServerPort);
			displayServerSocket = new ServerSocket(displayServerPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		new SensorClientSocketReceiver(this,sensorServerSocket);
		new DisplayClientSocketReceiver(this,displayServerSocket);
    }
    
    /** Updates the table with a new temperature observation.
     *  A time stamp is attached to the value before it is inserted in the table.
     * @param obs contains a temperature value and the location of its observation
     */
    public void update(TemperatureObservation obs) {
    	table.put(obs.location,new TimeStampedValue(obs.value));
    }
        
    /** Compiles all information in the table into a comprehensible form.
     * @return a text representation of the table contents.
     */
    public String getAll() {
    	Set<Map.Entry<String,TimeStampedValue>> es = table.entrySet();
    	String result = "";
    	for ( Map.Entry<String,TimeStampedValue> e : es ) {
    		String location = e.getKey();
    		TimeStampedValue vt = e.getValue();
    		String date = getDateAndTime(vt.getTime());
    		String temp = new Float(vt.getTemperature()).toString();
    		String line = location + " " +
    		              date + " " +
    		              (vt.getTemperature() > 0 ? "+" : "") +
    		              temp;
    		result += (line + "\n");
    	}
    	return result;
    }
    
    /** Extract date and time form an integer time value
     * @param t the time in standard long integer format
     * @return a string representation in the format DD/MM YYYY HH:MM
     */
    private String getDateAndTime(long t) {
    	Calendar date = Calendar.getInstance();
    	date.setTimeInMillis(t);
    	int year = date.get(Calendar.YEAR);
    	int month = date.get(Calendar.MONTH) + 1;
    	int day = date.get(Calendar.DAY_OF_MONTH);
    	int hour = date.get(Calendar.HOUR_OF_DAY);
    	int minute = date.get(Calendar.MINUTE);  	
    	return "" + day + "/" + month + " " + year + " " + hour + ":" + minute;
    }
}
