<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易卓博客 - 会员系统 - 文章管理 - 查看文章</title>
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
		<h1 class="page-title">查看文章</h1>
	</div>

	<ul class="breadcrumb">
		<li><a href="Main">会员系统</a> <span class="divider">/</span></li>
		<li><a href="Article?oper=list">文章管理</a> <span class="divider">/</span></li>
		<li class="active">查看文章</li>
	</ul>

	<div class="container-fluid">
		<div class="row-fluid">

			<h1>
				<span>${bean.title}</span>
			</h1>
			<p>
				<label>文章Id&nbsp;&nbsp;&nbsp;<span class="field">${bean.articleId}</span></label>
				<label>类目名称&nbsp;&nbsp;&nbsp;<span class="field">${bean.catName}</span></label> 
				<label>博主名称&nbsp;&nbsp;&nbsp;<span class="field">${bean.nickName}</span></label> 
				<label>发表时间&nbsp;&nbsp;&nbsp;<span	class="field">${bean.publishOn}</span></label> 
				<label>更新时间&nbsp;&nbsp;&nbsp;<span	class="field">${bean.updateOn}</span></label>
			</p>
			<hr />
			<p>${bean.content}</p>
			<input class="btn btn-primary radius" type="button"
				value="&nbsp;&nbsp;返回列表&nbsp;&nbsp;"
				onclick="javascript:location.href='Article?oper=list';" />
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