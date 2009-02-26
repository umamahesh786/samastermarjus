package dk.atisa.hs07.actuator;

import java.net.URL;

import dk.atisa.hs07.Invoker;
import dk.atisa.hs07.Service;

/**
 * 
 * Radiator service. A home may have a number of radiator services
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class RadiatorService extends Service {
	public Object getController() {
		return new Radiator();
	}
	
	/**
	 * Construct a radiator service and register the service with the gateway
	 * as an observer
	 * 
	 * @param gatewayLocation
	 * @param thisLocation
	 * @throws Exception
	 */
	public RadiatorService(String gatewayLocation, String thisLocation) throws Exception {
		super(thisLocation);
		URL url = new URL(thisLocation);
		Invoker.invoke(gatewayLocation, "registerObserver", "location", url.toString());
		System.out.println("Started radiator service at " + url);
	}

	public static void main(String[] args) throws Exception {
		URL baseUrl = new URL(args[1]);
		for (int i = 0; i < Integer.parseInt(args[2]); i++) {
			String location = "http://" + baseUrl.getHost() + ":" + (baseUrl.getPort() + i) + "/";
			new RadiatorService(args[0], location);
		}
	}
}
