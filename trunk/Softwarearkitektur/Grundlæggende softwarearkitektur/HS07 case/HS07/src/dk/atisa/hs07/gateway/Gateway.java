package dk.atisa.hs07.gateway;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.LinkedList;

import dk.atisa.hs07.Invoker;

/**
 * The controller for the gateway service. Implements the responsibilities
 * of the gateway service
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class Gateway implements Runnable {
	private static final long SLEEP_TIME = 1000;
	private Collection<String> thermometers = new LinkedList<String>();
	private Collection<String> observers = new LinkedList<String>();
	
	/**
	 * Register a thermometer which is used for estimating temperature
	 * 
	 * @param location of the thermometer. Must implement "getTemperature()"
	 */
	public void registerThermometer(String location) {
		thermometers.add(location);
	}

	/**
	 * Register an observer which is notified when a new temperature
	 * estimate is available
	 * 
	 * @param location of the observer. Must implement "notify(temperature)"
	 */
	public void registerObserver(String location) {
		observers.add(location);
	}

	/**
	 * Notify observers of temperature change
	 * 
	 * @param temperature
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public void notifyObservers(double temperature) throws MalformedURLException, IOException {
		for (String location : observers) {
			Invoker.invoke(location, "notify", "temperatur___e", "" + temperature);
		}
	}

	/**
	 * 
	 * @return the average of the temperatures measured by thermometers in 
	 * the home
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public double getTemperature() throws MalformedURLException, IOException {
		double sum = 0;
		for (String location : thermometers) {
			sum += Double.parseDouble(Invoker.invoke(location, "getTemperature"));
		}
		return sum/thermometers.size();
	}

	/**
	 * Run the control algorithm which is decentralized in this case, i.e.,
	 * observers are responsible for concluding based on temperature data
	 */
	public void run() {
		while (true) {
			try {
				Thread.sleep(SLEEP_TIME);
				if (thermometers.size() > 0) {
					double temperature = getTemperature();
					System.out.println("Average temperature: " + temperature + " for " + thermometers.size() + " thermometer(s)");
					notifyObservers(temperature);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
