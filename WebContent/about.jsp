<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>易卓博客 - 关于我们</title>
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
					<div>关于我们的左侧内容1</div>
					<h3>zuoce2</h3>
					<hr />
					<div>关于我们的左侧内容2</div>
				</div>

				<div class="right">
					<div class="blog">
						<h3>youce1</h3>
						<hr />
						<div>关于我们的右侧内容1</div>
					</div>
					<div class="blog">
						<h3>youce2</h3>
						<hr />
						<div>关于我们的右侧内容2</div>
					</div>

				</div>
				
			<div class="clr"></div>
		</div>
			<%@include file="_body_index_bottom.jsp"%>
		<%@include file="_body_footer.jsp"%>
	</div>
	</div>
	<script>
		$(function() {
			$(".header .menu ul li a").removeClass("active");
			$("#about").addClass("active");
		})
	</script>
</body>
</html>