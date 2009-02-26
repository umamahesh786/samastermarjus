package dk.atisa.hs07.sensor;

/**
 * An HS07 thermometer that may be queried for the current temperature
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class Thermometer {
	private double  temperature = 20;

	/**
	 * Simulate taking a temperature measurement
	 * 
	 * @return the current temperature
	 */
	public double getTemperature() {
		temperature += Math.random() - 0.5;
		return ((int)(temperature*10))/10.0;
	}
}
