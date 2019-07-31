
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="edu.uc.ui.ctrl.UIConst"%>
<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.ui.ctrl.*"%>
<%
	String loginName = "管理员";
	
	//从会话中获取指定key的登录用户数据
	Object objMember2 = session.getAttribute(UIConst.BG_LOGINUSER_KEY);
	//判断已经登录
	if (objMember2 != null) {
		Member member2 = (Member) objMember2;
		loginName = member2.getUserName();
	
	}
%>
<div class="topheader">
	<div class="left">
		<h1 class="logo">
			<span>易卓博客</span>
		</h1>
		<span class="slogan">易卓博客·后台管理系统</span>

		<div class="search">
			<form action="" method="post">
				<input type="text" name="keyword" id="keyword" value="请输入" />
				<button class="submitbutton"></button>
			</form>
		</div>
		<!--search-->

		<br clear="all" />

	</div>
	<!--left-->

	<div class="right">
		<!--<div class="notification">
                <a class="count" href="ajax/notifications.html"><span>9</span></a>
        	</div>-->
		<div class="userinfo">
			<img
				src="<%=application.getContextPath()%>/static/admin/images/thumbs/avatar.png"
				alt="" /> <span><%=loginName%></span>
		</div>
		<!--userinfo-->

		<div class="userinfodrop">
			<div class="avatar">
				<a href=""><img
					src="<%=application.getContextPath()%>/static/admin/images/thumbs/avatar.png"
					alt="" /></a>
				<div class="changetheme">
					切换主题: <br /> <a class="default"></a> <a class="blueline"></a> <a
						class="greenline"></a> <a class="contrast"></a> <a
						class="custombg"></a>
				</div>
			</div>
			<!--avatar-->
			<div class="userdata">
				<h4><%=loginName%></h4>
				<span class="email"></span>
				<ul>
					<li><a href="editprofile.html">编辑资料</a></li>
					<li><a href="accountsettings.html">账号设置</a></li>
					<li><a href="help.html">帮助</a></li>
					<li><a href="<%=application.getContextPath()%>/admin/loginOut.do">退出</a></li>
				</ul>
			</div>
			<!--userdata-->
		</div>
		<!--userinfodrop-->
	</div>
	<!--right-->
</div>
<!--topheader-->
<div class="header">
	<ul class="headermenu">
		<li class="current"><a href="dashboard.html"><span
				class="icon icon-flatscreen"></span>首页</a></li>
		<li><a href="manageblog.html"><span class="icon icon-pencil"></span>博客管理</a></li>
		<li><a href="messages.html"><span class="icon icon-message"></span>查看消息</a></li>
		<li><a href="reports.html"><span class="icon icon-chart"></span>统计报表</a></li>
	</ul>


</div>
<!--header-->
