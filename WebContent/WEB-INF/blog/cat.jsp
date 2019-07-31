<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--

Released for free under a Creative Commons Attribution 3.0 License
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>【${ViewBlogger.blogTitle} - ${CatBean.catName}】文章列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<jsp:include page="_head_link.jsp"></jsp:include>
<script>
	$(function() {
		$("#blog").addClass("active");
	})
</script>
<!-- CuFon ends -->
</head>
<body>
	<jsp:include page="_layout_header.jsp"></jsp:include>
	<div class="main">

		<jsp:include page="_ctx_header.jsp"></jsp:include>

		<div class="content">
			<!-- 内容开始 -->
			<div class="mainbar">

				<hr />
				<div class="post_list">
					<div
						style="font-size: 20px; margin: 12px 0px; border-bottom: 3px solid #4dc9dc;">
						<span>【${CatBean.catName}】文章列表</span>
					</div>
					<c:forEach var="item" items="${DataList}">
						<div class="post_item">
							<div class="digg">
								<div class="diggit" onclick="javascript:return false;">
									<span class="diggnum" id="digg_count_${item.articleId}">0</span>
								</div>
								<div class="clear"></div>
								<div id="digg_tip_${item.articleId}" class="digg_tip"></div>
							</div>
							<div class="post_item_body">

								<a class="titleInk"
									href="${AppRootPath}/blog/detail?f=${ViewBlogger.blogFolder}&articleid=${item.articleId}"
									target="_blank">${item.title}</a>
								<p class="post_item_summary">
									${fn:escapeXml(fn:substring(item.content,0,fn:length(item.content)>50?50:fn:length(item.content))) }
								</p>
								<div class="post_item_foot">
									<a href="#" class="lightblue">${item.nickName}</a>
									发布于:${item.publishOn} <span class="article_comment"><a
										href="#" title="" class="gray">评论(0)</a></span> <span
										class="article_view"><a href="#" class="gray">阅读(0)</a></span>
								</div>
							</div>
							<div class="clear"></div>
						</div>
					</c:forEach>
				</div>
				<!-- 内容结束 -->

				<jsp:include page="__pager.jsp" flush="true"></jsp:include>
			</div>
			<jsp:include page="_ctx_nav.jsp"></jsp:include>
			<div class="clr"></div>
			<jsp:include page="_ctx_footer.jsp"></jsp:include>

		</div>
	</div>
	<jsp:include page="_layout_footer.jsp"></jsp:include>

</body>
</html>