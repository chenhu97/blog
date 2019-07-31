<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.liuvei.common.*"%>

<%
	NewsService newsService = new NewsServiceImpl();
	List<News> vDataList = null;
%>
<%
	//左侧新闻列表
	//分页步骤1
	PagerItem pagerItem = new PagerItem();
	pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
	pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
	//分页步骤2.1
	Long rowCount = newsService.count();
	//分页步骤2.2 将记录的数据传给pagerItem,便于进行分页的各类计算
	pagerItem.changeRowCount(rowCount);
	//分页步骤2.3 从数据库中获取指定分页数据  方法根据实际改变
	vDataList = newsService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
	//分页步骤2.4 设置页面的跳转url 
	pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
	//分页步骤3 将分页对象和数据列表放在作用域，以便页面可以访问
	request.setAttribute("pagerItem", pagerItem);
	request.setAttribute("DataList", vDataList);
%>
<%
	//右侧新闻类目列表:取数据
	NewsCatService newsCatService = new NewsCatServiceImpl();
	List<NewsCat> catList = null;
	catList = newsCatService.list();
	request.setAttribute("catList", catList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>易卓博客 - 新闻中心</title>
<%@include file="_head_link.jsp"%>
</head>
<body>
	<div class="main">
		<%@include file="_body_header.jsp"%>

		<div class="body">
			<div class="body_resize">
				<div class="left">
					
					<span>所有新闻</span>
						
					<hr />
					<c:forEach var="item" items="${DataList}">
						<div style="line-height: 30px;">
							<a
								href="news_detail.jsp?id=${item.newsId}">${item.title}</a>
							&nbsp;&nbsp;&nbsp;&nbsp;<span>${item.updateOn}</span>
						</div>
					</c:forEach>
					<jsp:include page="__pager.jsp" flush="true" />
				</div>

				<div class="right">
					<div class="blog">
						<span>所有类目</span>
						<hr />
						<div>
							<c:forEach var="item" items="${catList}">
								<div>
									<a href="news_cat.jsp?id=${item.catId}">${item.catName}</a>
								</div>
							</c:forEach>
						</div>
					</div>
					<%@include file="_body_search_news.jsp"%>
				</div>
			</div>
			<div class="clr"></div>
		</div>
		<%@include file="_body_footer.jsp"%>
	</div>
	<script>
		$(function() {
			$(".header .menu ul li a").removeClass("active");
			$("#news").addClass("active");
		});
	</script>
</body>
</html>