<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登入页面</title>
<link rel="stylesheet"
	href="<%=application.getContextPath()%>/static/admin/css/style.default.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/custom/general.js"></script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/custom/index.js"></script>
</head>
<%
String userName="";
if(request.getAttribute("userName")!=null){
	userName=(String)request.getAttribute("userName");
	
}
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
	
}
%>
<body class="loginpage">
	<div class="loginbox">
		<div class="loginboxinner">
			<div style="color: red; font-weight: bold;">
			<%=msg%>
			</div>
			<form id="login" action="login.do" method="post">
			
				<div class="username">
					<div class="usernameinner">
						<input type="text" name="userName" id="userName" value="<%=userName%>">
					</div>
</div>
					<div class="password">
					<div class="passwordinner">
						<input type="password" name="userPass" id="userPass" value="">
					</div>
				</div>
				<button>登入</button>
				<div class="keep">
					<input type="checkbox" name="" id="">记住密码

				</div>
			</form>
		</div>
	</div>
</body>
</html>