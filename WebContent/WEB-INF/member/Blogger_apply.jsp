<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易卓博客 - 会员系统 - 申请博客</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="__head_link.jsp"></jsp:include>
<!-- Demo page code -->
</head>
<jsp:include page="_body_start.jsp"></jsp:include>
<!--<![endif]-->
<jsp:include page="_layout_top.jsp"></jsp:include>
<jsp:include page="_layout_header.jsp"></jsp:include>
<jsp:include page="_layout_nav.jsp"></jsp:include>




<div class="content">
	<div class="header">
		<jsp:include page="_ctx_header.jsp"></jsp:include>
		<h1 class="page-title">申请博客</h1>
	</div>
	<ul class="breadcrumb">
		<li><a href="Main">会员系统</a> <span class="divider">/</span></li>
		<li class="active">申请博客</li>
	</ul>

	<div class="container-fluid">
		<div class="row-fluid">
			<c:choose>
				<c:when test="${empty OTHER_LOGINUSER_KEY}">
					<!-- 当前没有申请，则为其提供申请表单 -->
					<form action="Blogger" id="tab" method="post">
						<input type="hidden" name="oper" value="applyDeal" />
						<div>
							<label>博主昵称:</label><input type="text" name="nickName"
								value="${nickName}" class="blog" />
						</div>
						<div>
							<label>博客主标题</label><input type="text" name="blogTitle"
								value="${blogTitle}" class="blog" />
						</div>
						<div>
							<label>博客副标题</label><input type="text" name="blogTitleEx"
								value="${blogTitleEx}" class="blog" />
						</div>
						<div>
							<label>博客路径</label><input type="text" name="blogFolder"
								value="${blogFolder}" class="blog" />
						</div>
						<div>
							<span style="color: red; font-weight: bold;">${msg}</span>
						</div>
						<div class="">
							<input class="btn btn-primary radius" type="submit"
								value="&nbsp;&nbsp;申请&nbsp;&nbsp;" /> <input
								class="btn btn-primary radius" type="reset"
								value="&nbsp;&nbsp;重置&nbsp;&nbsp;" />
						</div>
					</form>
				</c:when>

				<c:otherwise>
					<!-- 已经提交申请,则显示当前审核情况 -->
					<table>
						<tr>
							<td colspan="2">您已经申请了博客,当前状态为: <span
								style="color: red; font-weight: bold;">【${OTHER_LOGINUSER_KEY.isEnable==0?"审核中":"已开通"}】</span>
							</td>
						</tr>
						<tr>
							<td>博主昵称:</td>
							<td>${OTHER_LOGINUSER_KEY.nickName}</td>
						</tr>
						<tr>
							<td>博客主标题:</td>
							<td>${OTHER_LOGINUSER_KEY.blogTitle}</td>
						</tr>
						<tr>
							<td>博客副标题:</td>
							<td>${OTHER_LOGINUSER_KEY.blogTitleEx}</td>
						</tr>
						<tr>
							<td>博客路径:</td>
							<td>${OTHER_LOGINUSER_KEY.blogFolder}</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			<script type="text/javascript">
				$(function() {
					$(".blog").val("");

				});
			</script>

			<jsp:include page="_ctx_footer.jsp"></jsp:include>

		</div>
	</div>
</div>
<jsp:include page="_layout_footer.jsp"></jsp:include>
<jsp:include page="_layout_bottom.jsp"></jsp:include>
<script type="text/javascript">
	$("[rel=tooltip]").tooltip();
	$(function() {
		$('.demo-cancel-click').click(function() {
			return false;
		});
	});
</script>
<jsp:include page="_body_end.jsp"></jsp:include>