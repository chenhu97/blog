<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易卓 博客 - 会员登录</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="__head_link.jsp"></jsp:include>
<!-- Demo page code -->
</head>
<body class="">
	<div class="navbar">
		<div class="navbar-inner">
			<ul class="navbar-right">

			</ul>
			<a class="brand" href="Login"><span class="first">易卓</span> <span
				class="second">博客</span></a>
		</div>
	</div>




	<div class="row-fluid">
		<div class="dialog">
			<div class="block">
				<p class="block-heading">Sign In</p>
				<div class="block-body">
					<form id="login" action="Login" method="post">
						<input type="hidden" name="oper" value="loginDeal" /> <label>账号</label>
						<input type="text" class="span12" name="userName"
							value="${userName}" /> <label>密码</label> <input type="password"
							class="span12" name="userPass" />
						<div style="color: red; font-size: bold;">${msg}</div>
						<input type="submit" class="btn btn-primary pull-right" value="登录" />
						<label class="remember-me"><input type="checkbox" />记住密码</label>
						<div class="clearfix"></div>
					</form>
				</div>
			</div>
			<p>
				<a href="reset-password.html">Forgot your password?</a>
			</p>
		</div>
	</div>
</body>
</html>