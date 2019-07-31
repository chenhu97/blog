<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="edu.util.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.liuvei.common.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%!NewsService newsService = new NewsServiceImpl();
	NewsCatService newsCatService = new NewsCatServiceImpl();%>
<%
	List<News> vDataList;
	String strCatId = request.getParameter("id");
	Long catId = SysFun.parseLong(strCatId);
	//查找NewsCat是否存在这个CatId
	NewsCat cat = newsCatService.load(catId);
	if (cat == null) {
		response.sendRedirect("news_all.jsp");
		return;
	}
	request.setAttribute("cat", cat);
%>
<%
	//分页
	PagerItem pagerItem = new PagerItem();
	pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
	pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));

	Long rowCount = newsService.countBySearch(catId, null);

	pagerItem.changeRowCount(rowCount);

	vDataList = newsService.pagerBySearch(catId, null, pagerItem.getPageSize(), pagerItem.getPageNum());

	pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

	request.setAttribute("pagetItem", pagerItem);
	request.setAttribute("DataList", vDataList);
	List<NewsCat> catList = newsCatService.list();
	request.setAttribute("catList", catList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>易卓博客</title>
<%@include file="_head_link.jsp"%>
<script>
	$(function() {
		$(".header .menu ul li a").removeClass("active");
		$("#news").addClass("active");
	});
</script>
</head>
<body>
	<div class="main">
		<%@include file="_body_header.jsp"%>

		<div class="body">
			<div class="body_resize">
				<div class="left">
					<h2>${cat.catName}的新闻</h2>
					<span>${cat.catName}的新闻</span>

					<hr />
					<c:forEach var="item" items="${DataList}">
						<div>
							<a href="news_detail.jsp?id=${item.newsId}">${item.title}</a>
						</div>
						<p class="big bgline">${item.summary}&nbsp;&nbsp;&nbsp;&nbsp;发布时间:${item.updateOn}</p>
					</c:forEach>
				</div>
				<div class="right">
					<div class="blogs">
						<h2>新闻类目</h2>
						<hr />
						<c:forEach var="item" items="${catList}">
							<div>
								<a href="news_cat.jsp?id=${item.catId}">${item.catName}</a>
							</div>
						</c:forEach>
					</div>
					<%@include file="_body_search_news.jsp"%>
				</div>
			</div>
			<div class="clr"></div>
		</div>
		<%@include file="_body_footer.jsp"%>
	</div>

</body>
</html>