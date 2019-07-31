package edu.blog.ui.counter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import edu.blog.bean.Counter;
import edu.blog.service.CounterService;
import edu.blog.service.impl.CounterServiceImpl;

/**
 * 主要用于从DB中写计数值
 * 
 * Application Lifecycle Listener implementation class CounterAppListener
 *
 */
@WebListener
public class CounterAppListener implements ServletContextListener {

	CounterService counterService = new CounterServiceImpl();

	/**
	 * Default constructor.
	 */
	public CounterAppListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		javax.servlet.ServletContext application = arg0.getServletContext();
		// 1) 获取application的counter对象
		Counter counter = (Counter) application.getAttribute("counter");
		// 2) 存在，则内存中的对象写到数据库中
		if (counter != null) {
			counterService.writeCount(counter);
		}

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		javax.servlet.ServletContext application = arg0.getServletContext();
		// 1)从DB中读取counter对象
		Counter counter = counterService.readCount();
		// 2)counter对象放到上下文application作用域
		application.setAttribute("counter", counter);

	}

}
