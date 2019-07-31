<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>模板</title>
<%@include file="_head_link.jsp"%>
</head>
<body>
	<div class="main">
		<%@include file="_body_header.jsp"%>

		<div class="body">
			<div class="body_resize">
				<div class="left">
					<h3>
						<span>zuoce1</span> License
					</h3>
					<hr />
					<div>左侧内容1</div>
					<h3>zuoce2</h3>
					<hr />
					<div>左侧内容2</div>
				</div>

				<div class="right">
					<div class="blog">
						<h3>youce1</h3>
						<hr />
						<div>右侧内容1</div>
					</div>
					<div class="blog">
						<h3>youce2</h3>
						<hr />
						<div>右侧内容2</div>
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