<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="header">
	<div class="logo">

		<a href="index" style="font-size: 20px; text-decoration: none;">${ViewBlogger.blogTitle}<br />
			<small>${ViewBlogger.blogTitleEx}</small>
		</a>

	</div>
	<div class="header_block">
		<div class="search">
			<form method="post" id="search" action="search">
				<span> <input type="hidden" name="f"
					value="${ViewBlogger.blogFolder}" /> <input type="text"
					placeholder="Search..." value="${searchKey}" name="s"
					id="s" /> <input name="searchsubmit" type="image"
					src="<%=application.getContextPath()%>/static/site/images/search.gif"
					value="Go" id="searchsubmit" class="btn" />
				</span>
			</form>
			<!--/searchform -->
			<div class="clr"></div>
		</div>
		<div class="clr"></div>
		<div class="menu_nav">
			<ul>
				<li><a href="">易卓博客</a></li>
				<li id="index"><a href="index?f=${ViewBlogger.blogFolder}">主页</a></li>
				<li id="blog"><a href="blog?f=${ViewBlogger.blogFolder}">文章</a></li>
				<li id="contact"><a href="contact?f=${ViewBlogger.blogFolder}">联系方式</a></li>
				<li id="about"><a href="about?f=${ViewBlogger.blogFolder}">关于博主</a></li>
			</ul>
		</div>
		<div class="menu_nav">
			<c:forEach var="item" items="Top5NewsList">

			</c:forEach>
		</div>
		<div class="clr"></div>
	</div>
	<div class="clr"></div>
</div>

