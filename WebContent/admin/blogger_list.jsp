<%@page import="edu.blog.service.BloggerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>
<%@include file="__checkLoginAndIsAdmin2.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统后台 - 博客开通申请管理</title>
<%@include file="_head_link.jsp"%>
</head>

<%@include file="_body_start.jsp"%>
<%@include file="_ctx_header.jsp"%>
<%@include file="_ctx_nav.jsp"%>

<%
	BloggerService bloggerService = new BloggerServiceImpl();
	List<Blogger> vDataList = bloggerService.listByIsEnable(0L);
	request.setAttribute("DataList", vDataList);
%>
<div class="centercontent">

	<div class="pageheader">
		<h1 class="pagetitle">系统后台 - 博客开通申请管理</h1>
		<span class="pagedesc">处理博主申请</span>
	</div>
	<!--pageheader-->
	<table border="1" cellspacing="0" cellpadding="8" class="stdtable">
		<tr>
			<td><input type="checkbox" id="checkall" name="checkall"
				value="" /></td>
			<td class="center">会员Id</td>
			<td class="center">博主昵称</td>
			<td class="center">博主标题</td>
			<td class="center">博主副标题</td>
			<td class="center">博主路径</td>
			<td class="center">是否审核</td>
			<td class="center">&nbsp;&nbsp;操作</td>
		</tr>
		<c:forEach var="bean" items="${DataList}">
			<tr>
				<td><input type="checkbox" value="" /></td>
				<td class="center">${bean.memberId}</td>
				<td class="center">${bean.nickName}</td>
				<td class="center">${bean.blogTitle}</td>
				<td class="center">${bean.blogTitleEx}</td>
				<td class="center">${bean.blogFolder}</td>
				<td class="center" style="color: red; font-weight: bold;">未审核</td>
				<td class="center">
					<a href="Blogger?oper=passDeal&id=${bean.memberId}" class="edit" >审核通过</a>
				</td>
			</tr>

		</c:forEach>

	</table>

	<br clear="all" />

</div>
<!-- centercontent -->
<%@include file="_ctx_footer.jsp"%>
<%@include file="_body_end.jsp"%>