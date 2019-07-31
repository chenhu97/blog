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
	String searchName = request.getParameter("searchName");
	searchName = searchName == null ? "": searchName;//为null时，则赋值为""
%>
<%
	//左侧新闻列表
	//分页步骤1
	PagerItem pagerItem = new PagerItem();
	pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
	pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
	if(SysFun.isNullOrEmpty(searchName)){
		//为空时采用普通分页方法
		//分页步骤2.1 根据条件，查找符合条件的所有记录数  *****count()要根据实际换成其他方法pagerItem
		Long rowCount = newsService.count();
		//分页步骤2.2 将记录的数据传给pagerItem,便于进行分页的各类计算
		pagerItem.changeRowCount(rowCount);
		//分页步骤2.3 从数据库中获取指定分页数据  方法根据实际改变
		vDataList = newsService.pager(pagerItem.getPageNum(),pagerItem.getPageSize());
		//分页步骤2.4 设置页面的跳转url 
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
	}else{
		//不为空，则采用搜索分页方法
		Long rowCount = newsService.countByName(searchName);
		
		pagerItem.changeRowCount(rowCount);
		
		vDataList = newsService.pagerByName(searchName, pagerItem.getPageNum(),pagerItem.getPageSize());
		
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
	}
	//分页步骤3 将分页对象和数据列表放在作用域，以便页面可以访问
	request.setAttribute("pagerItem", pagerItem);
	request.setAttribute("DataList", vDataList);
	request.setAttribute("searchName", searchName);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>易卓博客 -　新闻搜索 - 关键字为</title>
<%@include file="_head_link.jsp"%>
</head>
<body>
	<div class="main">
		<%@include file="_body_header.jsp"%>

		<div class="body">
			<div class="body_resize">
				<div class="left">

					<span>所有新闻——含关键字【${searchName}】</span>
					<c:forEach var="item" items="${DataList}">
						<div style="line-height: 30px;">
							<a href="news_detail.jsp?id=${item.newsId}">${item.title}</a>
							&nbsp;&nbsp;&nbsp;&nbsp;<span>${item.updateOn}</span><br />
						</div>
					</c:forEach>
					<jsp:include page="__pager.jsp" flush="true"></jsp:include>

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
		})
	</script>
</body>
</html>