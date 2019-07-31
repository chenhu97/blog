package edu.blog.ui.counter;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import edu.blog.bean.Counter;

/**
 * 
 * 
 * 每次开始新的session,计数器加1 Application Lifecycle Listener implementation class
 * CounterSessionListener
 *
 */
@WebListener
public class CounterSessionListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public CounterSessionListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		javax.servlet.http.HttpSession session = arg0.getSession();
		javax.servlet.ServletContext application = session.getServletContext();
		// 1)取得application的counter对象
		Counter counter = (Counter) application.getAttribute("counter");
		// 2) 存在,则内存中的对象num值加1
		if (counter != null) {
			counter.setNum(counter.getNum() + 1);
		}

	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
