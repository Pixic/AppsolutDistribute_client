package weather_server;

/**
 * This class sticks a time stamp to a temperature value.
 * @author  Uno Holmer
 * @version 2008-02-07
 */
public class TimeStampedValue {

	private float temperature;
	private long time;
	
	public TimeStampedValue(float temperature) {
		this.temperature = temperature;
		time = System.currentTimeMillis();
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public long getTime() {
		return time;
	}
}
