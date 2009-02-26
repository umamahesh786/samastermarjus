package dk.atisa.hs07.actuator;

/**
 * An HS07 radiator that may be turned on and off
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class Radiator {
	/**
	 * Maximum temperature for control algorithm
	 */
	public static final double MAX_TEMPERATURE = 20.5;
	/**
	 * Minimum temperature for control algorithm
	 */
	public static final double MIN_TEMPERATURE = 19.5;

	private boolean state = false;

	/**
	 * Run the control algorithm upon notification of temperature change
	 * 
	 * @param _temperature
	 */
	public void notify(String _temperature) {
		double temperature = Double.parseDouble(_temperature);
		if (temperature < MIN_TEMPERATURE) {
			System.out.println("Turn on radiator");
			setState(true);
		} else if (temperature > MAX_TEMPERATURE) {
			System.out.println("Turn off radiator");
			setState(false);
		} 
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return state;
	}
}
