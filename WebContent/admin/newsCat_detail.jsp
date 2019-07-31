<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="__checkLoginAndIsAdmin2.jsp"%>

<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%
	NewsCatService newsCatService = new NewsCatServiceImpl();

	String id = request.getParameter("id");
	
	// 2) 服务端验证:主键的为空性判断
	if (id == null || id.isEmpty()) {
		out.print("<script>alert('id不能为空。');location.href='newsCat_list.jsp';</script>");
		return;
	}
	// 3) 服务端验证:主键的合法性判断
	Long vId = 0L;
	try {
		vId = Long.parseLong(id);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		out.print("<script>alert('id不是数字。');location.href='newsCat_list.jsp';</script>");
		return;
	}
	// 4) 业务功能: 从数据库获取数据
	NewsCat bean = newsCatService.load(vId);

	if (bean == null) {
		out.print("<script>alert('不存在对应的数据。');location.href='newsCat_list.jsp';</script>");
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
		<h1 class="pagetitle">系统后台 - 类目管理 - 查看</h1>
		<span class="pagedesc">查看类目数据</span>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">
		<p>
			<label>Id</label> <span class="field"> <%=bean.getCatId()%></span>
		</p>
		<p>
			<label>类目名称</label> <span class="field"> <%=bean.getCatName()%></span>
		</p>
		<p>
			<label>类目描述</label> <span class="field"> <%=bean.getCatDesc()%></span>
		</p>
		<p>
			<label>状态</label> <span class="field"> <%=bean.getStatus()%></span>
		</p>
		<p class="stdformbutton">
			<button onclick="javascript:location.href='newsCat_list.jsp';">返回列表</button>
		</p>
	</div>
	<!--contentwrapper-->

	<br clear="all" />


	<%@include file="_ctx_footer.jsp"%>
	<%@include file="_body_end.jsp"%>