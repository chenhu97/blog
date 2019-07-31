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

					<span>新闻中心</span>
					<a style="font-size: 15px;" href="news_all.jsp">查看所有新闻</a>
					<hr />
					<%
						for (NewsCat tmpCat : catList) {
							//查找某个类目下的前n条新闻列表
							List<News> tmpListNews = newsService.listByCatId_limit_n(tmpCat.getCatId(), 5L);
							//类目下存在新闻才将对应类目和新闻类目显示
							if (tmpListNews != null && tmpListNews.size() > 0) {
					%>
					<span><%=tmpCat.getCatName()%></span>
					
					<%
							//内部遍历类目对应的前n条新闻列表
							for (News beanNews : tmpListNews) {
					%>
					<div>
						<a href="news_detail.jsp?id=<%=beanNews.getNewsId()%>"><%=beanNews.getTitle()%></a>
					</div>
					<p class="big bgline"><%=beanNews.getSummary()%>&nbsp;&nbsp;&nbsp;&nbsp;发布时间:<%=beanNews.getUpdateOn()%></p>
					<%
						}
					}
				}
					%>
				</div>

				<div class="right">
					<div class="blogs">
						<span>新闻类目</span>
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