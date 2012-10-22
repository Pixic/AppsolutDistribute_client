package weatherlib;

import java.io.Serializable;

public class TemperatureObservation implements Serializable {
	private static final long serialVersionUID = 1L;
	public String location;
	public float value;
	
	public TemperatureObservation(String location, float value) {
		this.location = location;
		this.value = value;
	}
}
