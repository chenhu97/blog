
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="edu.uc.ui.ctrl.UIConst"%>
<%@ page import="edu.blog.bean.*" %>
<%@ page import="edu.blog.ui.ctrl.*" %>

<%
	//从会话中获取指定key的登录用户数据
	Object objMember = session.getAttribute(UIConst.BG_LOGINUSER_KEY);
	
	//判断有没有登录
	if(objMember==null){
		//没有正常登录
		response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		return;
	}
	//如果正常登录，则目前判断是否是管理员登陆
	Member member = (Member)objMember;
	if(!member.getUserName().equalsIgnoreCase("admin")){
		//如果不是管理员
		session.removeAttribute(UIConst.BG_LOGINUSER_KEY);
		response.sendRedirect(request.getContextPath() + "/admin/login.jsp");
		return;
	}



%>