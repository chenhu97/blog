<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="__checkLoginAndIsAdmin2.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员后台 - 显示计数器</title>
<%@include file="_head_link.jsp"%>
</head>

<%@include file="_body_start.jsp"%>
<%@include file="_ctx_header.jsp"%>
<%@include file="_ctx_nav.jsp"%>
<div class="centercontent">

	<div>当前计数器为:${counter.num}</div>
	<div>计数器规则:</div>
	<div>1) 上下文启动时,从DB中获取计数器对象;</div>
	<div>2) 每开新的会话session,则计数器加1;</div>
	<div>3) 上下文关闭时,将计数器对象写回DB。</div>

	<br clear="all" />

</div>
<!-- centercontent -->
<%@include file="_ctx_footer.jsp"%>
<%@include file="_body_end.jsp"%>