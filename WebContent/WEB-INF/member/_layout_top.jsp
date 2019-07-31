<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav pull-right">

			<li><a href="#"
				class="hidden-phone visible-tablet visible-desktop" role="button">Settings</a></li>
			<li id="fat-menu" class="dropdown"><a href="#" role="button"
				class="dropdown-toggle" data-toggle="dropdown"> <i
					class="icon-user"></i>${sessionScope.FG_LOGINUSER_KEY.nickName}<i class="icon-caret-down"></i>
			</a>

				<ul class="dropdown-menu">
					<li><a tabindex="-1" href="#">${sessionScope.FG_LOGINUSER_KEY.userName}</a></li>
					<li class="divider"></li>
					<li><a tabindex="-1" class="visible-phone" href="#">Settings</a></li>
					<li class="divider visible-phone"></li>
					<li><a tabindex="-1" href="LogOut">Logout</a></li>
				</ul></li>

		</ul>
		<a class="brand" href="index.html"><span class="first">易卓</span>
			<span class="second">博客</span></a>
	</div>
</div>
    