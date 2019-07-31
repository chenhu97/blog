<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="sidebar">
	<div class="gadget">
		<div>个人分类</div>
		<!-- forEach显示所属类目 -->
		<ul class="sb_menu">
			<c:forEach var="item" items="${ViewCatList}">
				<li><a
					href="cat?f=${ViewBlogger.blogFolder}&catid=${item.catId}">${item.catName}</a></li>
			</c:forEach>
		</ul>
		<div class="clr"></div>
	</div>
	<div class="gadget">

		<div>最新新闻</div>
		<ul class="sb_menu">
			<c:forEach var="item" items="${Top5NewsList}">
				<li><a
					href="${AppRootPath}/news_detail.jsp?id=${item.newsId}">${item.title}</a>
				</li>
			</c:forEach>
		</ul>
		<div class="clr"></div>
		<ul class="ex_menu">

		</ul>
	</div>
</div>

