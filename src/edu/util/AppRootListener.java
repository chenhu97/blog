package edu.util;

import javax.servlet.ServletContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AppRootListener
 *
 */
@WebListener
public class AppRootListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public AppRootListener() {
		// TODO Auto-generated constructor stub

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application = sce.getServletContext();
		application.setAttribute("AppRootPath", application.getContextPath());
		application.setAttribute("AppRootDir", application.getRealPath("/"));
	}

}
