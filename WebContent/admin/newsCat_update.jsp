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
<%
	Object obj_catName = request.getAttribute("catName");

	Object obj_catDesc = request.getAttribute("catDesc");

	Object obj_status = request.getAttribute("status");

	Object obj_msg = request.getAttribute("msg");

	String catName = obj_catName == null ? "" : (String) obj_catName;
	String catDesc = obj_catDesc == null ? "" : (String) obj_catDesc;
	String status = obj_status == null ? "" : (String) obj_status;
	String msg = obj_msg == null ? "" : (String) obj_msg;

	//如果不存在回显数据，则使用原始的记录中的数据
	catName = catName.isEmpty() ? bean.getCatName() : catName;
	catDesc = catDesc.isEmpty() ? bean.getCatDesc() : catDesc;
	status = status.isEmpty() ? bean.getStatus() : status;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统后台 - 类目管理 - 修改</title>
<%@include file="_head_link.jsp"%>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery.validate.min.js">
	
</script>

<style>
.RedBold {
	color: red;
	font-weight: bold;
}
</style>

</head>

<%@include file="_body_start.jsp"%>
<%@include file="_ctx_header.jsp"%>
<%@include file="_ctx_nav.jsp"%>
<div class="centercontent">

	<div class="pageheader">
		<h1 class="pagetitle">系统后台 - 类目管理 - 修改</h1>
		<span class="pagedesc">修改类目数据</span>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">

		<form id="myform" action="newsCat_update.do" method="post">
			<input type="hidden" id="id" name="id" value="<%=id%>" />
			<p class="RedBold">
				<%=msg%>
			</p>
			<p>
				<label for="catName">类目名称</label><span class="field"> <input
					type="text" id="catName" name="catName" value="<%=catName%>" />
				</span>
			</p>
			<p>
				<label for="catDesc">类目描述</label><span class="field"> <input
					type="text" id="catDesc" name="catDesc" value="<%=catDesc%>" /></span>
			</p>
			<p>
				<label for="status">状态</label><span class="field"> <input
					type="text" id="status" name="status" value="<%=status%>" /></span>
			</p>
			<br clear="all" /> <br />
			<p class="stdformbutton">
				<input type="submit" class="submit radius2" value="提交" />
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="reset radius2"
					value="重置" />
			</p>
		</form>
	</div>
	<!--contentwrapper-->
	<br clear="all" />

	<%@include file="_ctx_footer.jsp"%>
	<script>
		$(function() {
			var validate = $("#myform").validate({
				debug : true,
				errorClass : "RedBold",
				focusInvalid : false,
				onkeyup : false,
				submitHandler : function(form) {
					form.submit();
				},
				rules : {
					catName : {
						required : true
					},
					catDesc : {
						required : true
					}
				},
				messages : {
					catName : {
						required : "类目名称必填"
					},
					catDesc : {
						required : "类目描述必填"
					}

				}

			});

		});
	</script>
	<%@include file="_body_end.jsp"%>