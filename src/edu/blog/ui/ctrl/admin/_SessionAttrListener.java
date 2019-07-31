package edu.blog.ui.ctrl.admin;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import edu.blog.bean.Member;

/**
 * Application Lifecycle Listener implementation class SessionAttrListener
 *
 */
//@WebListener("/admin/login.jsp")
public class _SessionAttrListener implements HttpSessionAttributeListener {

	/**
	 * Default constructor.
	 */
	public _SessionAttrListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		String key = arg0.getName();
		Object value = arg0.getValue();
		if (value != null && value instanceof Member) {
			Member bean = (Member) value;
			System.out.println("【attributeAdded】" + key + "=" + bean.getUserName());
		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		String key = arg0.getName();
		Object value = arg0.getValue();
		if (value != null && value instanceof Member) {
			Member bean = (Member) value;
			System.out.println("【attributeRemoved】" + key + "=" + bean.getUserName());
		}
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub

	}

}
