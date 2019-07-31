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
<title>【${ViewBlogger.blogTitle} - ${articleBean.title}】</title>
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
						<span>${articleBean.title}</span>
					</div>
					<div>${articleBean.content}</div>
				</div>
				<!-- 内容结束 -->
			</div>
			<jsp:include page="_ctx_nav.jsp"></jsp:include>
			<div class="clr"></div>
			<jsp:include page="_ctx_footer.jsp"></jsp:include>

		</div>
	</div>
	<jsp:include page="_layout_footer.jsp"></jsp:include>

</body>
</html>