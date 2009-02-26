package dk.atisa.hs07;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Support for implementing a service using the HS07 protocol
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class ProtocolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object controller;

	public ProtocolServlet(Object controller) {
		this.controller = controller;
	}

	/**
	 * 
	 * Lookup method on controller based on parameters of request URL
	 * 
	 * The method is invoked using reflection and the invocation is neither type safe, nor
	 * safe regarding number of arguments
	 * 
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean found = false;
		String methodName = req.getRequestURI().substring(1);
		for (Method method: controller.getClass().getDeclaredMethods()) {
			if (method.getName().equals(methodName)) {
				found = true;
				try {
					Object[] parameters = new String[req.getParameterMap().keySet().size()];
					int i = 0;
					for (Object key: req.getParameterMap().keySet()) {
						parameters[i++] = req.getParameterValues((String)key)[0];
					}
					Object result = method.invoke(controller, parameters);
					if (result != null) {
						resp.getWriter().append(result.toString());
					}
				} catch (Exception e) {
					resp.getWriter().append(e.toString());
				}
			}
		}
		if (!found) {
			resp.getWriter().append("java.lang.NoSuchMethodException: " + methodName);
		}
	}
}
