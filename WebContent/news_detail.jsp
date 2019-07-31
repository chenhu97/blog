<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%
NewsService newsService = new NewsServiceImpl();

String id = request.getParameter("id");
//2) 服务端验证:主键的为空性判断
	if (id == null || id.isEmpty()) {
		out.print("<script>alert('id不能为空。');location.go(-1);</script>");
		return;
	}
	// 3) 服务端验证:主键的合法性判断
	Long vId = 0L;
	try {
		vId = Long.parseLong(id);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		out.print("<script>alert('id不是数字。');location.go(-1);</script>");
		return;
	}
	// 4) 业务功能: 从数据库获取数据
	News bean = newsService.load(vId);

	if (bean == null) {
		out.print("<script>alert('不存在对应的数据。');location.go(-1);</script>");
		return;
	}
	List<News> vDataList = null;
%>

<%
	//左侧新闻详情之内容:取数据
	NewsExContService newsExContService = new NewsExContServiceImpl();
	NewsExCont beanEx = newsExContService.loadByNewsId(vId);
	request.setAttribute("bean", bean);
	request.setAttribute("beanEx", beanEx);
	
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
<title>易卓博客 - 新闻中心 -${bean.title}</title>
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
				
					<span>${bean.title}</span>
				
					<hr />
					<div style="margin:0px auto;text-align:right;">
					作者:${bean.author}&nbsp;&nbsp;&nbsp;&nbsp;更新时间${bean.updateOn}<br/>
					</div>
					<p>${beanEx.content}</p>
					<c:if test="${not empty bean.picPath}">
						<div>
							<img style="width=300px;" src="${pageContext.request.contextPath}${bean.picPath}"/>
						</div>
					</c:if>
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
	
</body>
</html>