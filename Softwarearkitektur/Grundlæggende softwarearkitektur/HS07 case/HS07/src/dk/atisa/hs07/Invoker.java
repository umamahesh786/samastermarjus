package dk.atisa.hs07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Support for invoking a service with the HS07 protocol
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class Invoker {
	/**
	 * Invoke a service using the HS07 protocol
	 * 
	 * HTTP GET is used and the resource at 
	 * 
	 * 	<location><method>[?key0=value0&...&keyN=valueN]
	 * 
	 * is requested. The response to HTTP GET is assumed to be a single line
	 * the response
	 * 
	 * @param location of the service
 	 * @param method to be invoked
	 * @param parameters of the service on the form of key0, value0, ..., keyN, valueN
	 * @return the result of invoking the service (as a String)
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static String invoke(String location, String method, String ... parameters) throws MalformedURLException, IOException {
		String result = null;
		StringBuffer parameterString = new StringBuffer();
		if (parameters != null) {
			parameterString.append("?");
			for (int i = 0; i < parameters.length; i = i + 2) {
				parameterString.append(parameters[i] + "=" + parameters[i + 1]);
				if (i != parameters.length - 2) {
					parameterString.append("&");
				}
			}
		}
		URLConnection connection = new URL(location + method + parameterString).openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		result = in.readLine();
		in.close();
		return result;
	}
}
