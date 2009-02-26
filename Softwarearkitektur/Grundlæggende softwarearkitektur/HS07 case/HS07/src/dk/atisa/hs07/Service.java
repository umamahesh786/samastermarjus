package dk.atisa.hs07;

import java.net.URL;

import javax.servlet.Servlet;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 * An HS07 service
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public abstract class Service {
	/**
	 * Creates and starts an HS07 service using the Jetty servlet container
	 * 
	 * @param location of the service
	 * @throws Exception
	 */
	public Service(String location) throws Exception {
		Server server = new Server(new URL(location).getPort());    
		Context root = new Context(server, "/", Context.SESSIONS);
		Servlet servlet = new ProtocolServlet(getController());
		
		root.addServlet(new ServletHolder(servlet), "/*");
		server.start();
	}

	/**
	 * Override this to provide a POJO that implements this service
	 * 
	 * @return the controller
	 */
	public abstract Object getController();
}
