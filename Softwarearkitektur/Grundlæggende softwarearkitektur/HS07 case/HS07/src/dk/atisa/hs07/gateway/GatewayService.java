package dk.atisa.hs07.gateway;

import dk.atisa.hs07.Service;

/**
 * 
 * Gateway service. A home has one gateway service. It has three responsibilities:
 * 
 * - providing an Internet access point for the home from the outside
 * - running a control algorithm for the heating system
 * - providing a known address to other services in the home
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class GatewayService extends Service {
	public GatewayService(String gatewayLocation) throws Exception {
		super(gatewayLocation);
		System.out.println("Started gateway service at " + gatewayLocation);
	}

	/**
	 * Start the controller as a Thread since we run the control
	 * algorithm continuously
	 */
	public Object getController() {
		Runnable controller = new Gateway();
		new Thread(controller).start();
		return controller;
	}
	
	public static void main(String[] args) throws Exception {
		new GatewayService(args[0]);
	}
}
