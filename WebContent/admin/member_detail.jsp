<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@include file="__checkLoginAndIsAdmin2.jsp"%>

<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%
	MemberService memberService = new MemberServiceImpl();
	String id = request.getParameter("id");
	// 2) 服务端验证:主键的为空性判断
	if (id == null || id.isEmpty()) {
		out.print("<script>alert('id不能为空。');location.href='member_list.jsp';</script>");
		return;
	}
	// 3) 服务端验证:主键的合法性判断
	Long vId = 0L;
	try {
		vId = Long.parseLong(id);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		out.print("<script>alert('id不是数字。');location.href='member_list.jsp';</script>");
		return;
	}
	// 4) 业务功能: 从数据库获取数据
	Member bean = memberService.load(vId);

	if (bean == null) {
		out.print("<script>alert('不存在对应的数据。');location.href='member_list.jsp';</script>");
		return;
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员后台主页面</title>
<%@include file="_head_link.jsp"%>
</head>

<%@include file="_body_start.jsp"%>
<%@include file="_ctx_header.jsp"%>
<%@include file="_ctx_nav.jsp"%>
<div class="centercontent">

	<div class="pageheader">
		<h1 class="pagetitle">系统后台 - 会员管理 - 查看</h1>
		<span class="pagedesc">查看会员数据</span>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">
		<p>
			<label>账号</label> <span class="field"> <%=bean.getUserName()%></span>
		</p>
		<p>
			<label>昵称</label> <span class="field"> <%=bean.getNickName()%></span>
		</p>
		<p>
			<label>Email</label> <span class="field"><%=bean.getEmail()%></span>
		</p>
		<p class="stdformbutton">
			<button onclick="javascript:location.href='member_list.jsp';">返回列表</button>
		</p>
	</div>
	<!--contentwrapper-->

	<br clear="all" />


	<%@include file="_ctx_footer.jsp"%>
	<%@include file="_body_end.jsp"%>