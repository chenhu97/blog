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
<%
	
	Object obj_userName = request.getAttribute("userName");
	Object obj_nickName = request.getAttribute("nickName");
	Object obj_email = request.getAttribute("email");
	Object obj_msg = request.getAttribute("msg");

	
	String userName = obj_userName == null ? "" : (String) obj_userName;
	String nickName = obj_nickName == null ? "" : (String) obj_nickName;
	String email = obj_email == null ? "" : (String) obj_email;
	String msg = obj_msg == null ? "" : (String) obj_msg;

	//如果不存在回显数据，则使用原始的记录中的数据
	userName = userName.isEmpty() ? bean.getUserName() : userName;
	nickName = nickName.isEmpty() ? bean.getNickName() : nickName;
	email = email.isEmpty() ? bean.getEmail() : email;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统后台 - 会员管理 - 修改</title>
<%@include file="_head_link.jsp"%>
<script type="text/javascript" src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery.validate.min.js">
	
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
		<h1 class="pagetitle">系统后台 - 会员管理 - 修改</h1>
		<span class="pagedesc">修改会员数据</span>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">

		<form id="myform" action="member_update.do" method="post">
		
			<p class="RedBold">
				<%=msg%>
			</p>
				<input type="hidden" id="id" name="id" value="<%=id%>" />
			<p>
				<label for="userName">账号</label><span class="field"> <input
					type="text" id="userName" name="userName" value="<%=userName%>" />
				</span>
			</p>
			<p>
				<label for="userPass">密码</label><span class="field"> <input
					type="password" id="userPass" name="userPass" class="mediuminput" /></span>
			</p>
			<p>
				<label for="userPass2">确认密码</label><span class="field"> <input
					type="password" id="userPass2" name="userPass2" class="mediuminput" /></span>
			</p>
			<p>
				<label for="nickName">昵称</label><span class="field"><input
					type="text" id="nickName" name="nickName" value="<%=nickName%>" /></span>
			</p>
			<p>
				<label for="email">Email</label><span class="field"><input
					type="text" id="email" name="email" class="mediuminput"
					value="<%=email%>" /></span>
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
					userName : {
						required : true
					},
					userPass : {
						//required : true,
						minlength : 6
					},
					userPass2 : {
						//required : true,
						equalTo : "#userPass"
					},
					nickName : {
						required : true
					},
					email : {
						required : true,
						email : true
					}
				},
				messages : {
					userName : {
						required : "账号必填"
					},
					userPass : {
						//required : "密码必填",
						minlength : "长度最少6位"
					},
					userPass2 : {
						//required : "确认密码必填",
						equalTo : "密码和确认密码必须一致"
					},
					nickName : {
						required : "昵称必填"
					},
					email : {
						required : "Email必填",
						email : "E-Mail格式不正确"
					}

				}

			});

		});
	</script>
</div>
	<%@include file="_body_end.jsp"%>