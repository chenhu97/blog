package edu.blog.ui.ctrl.admin;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

import edu.blog.bean.Member;

/**
 * Application Lifecycle Listener implementation class RequestAttrListener
 *
 */
//@WebListener("/admin/member_list.jsp")
public class _RequestAttrListener implements ServletRequestAttributeListener {

    /**
     * Default constructor. 
     */
    public _RequestAttrListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
     */
    public void attributeRemoved(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
//    	String key = arg0.getName();
//		Object value = arg0.getValue();
		
			
			System.out.println("【requestAttributeRemoved】" + arg0.getName() + "','" + arg0.getValue()) ;
		
    }

	/**
     * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
     */
    public void attributeAdded(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
		System.out.println("【requestAttributeAdded】" + arg0.getName() + "','" + arg0.getValue()) ;
	}
    

	/**
     * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
     */
    public void attributeReplaced(ServletRequestAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
