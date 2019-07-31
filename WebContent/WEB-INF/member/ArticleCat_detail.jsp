<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易卓博客 - 会员系统 - 类目管理 - 查看类目</title>
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
		<h1 class="page-title">查看类目</h1>
	</div>

	<ul class="breadcrumb">
		<li><a href="Main">会员系统</a> <span class="divider">/</span></li>
		<li><a href="ArticleCat?oper=list">类目管理</a><span class="divider">/</span></li>
		<li class="active">查看类目</li>
	</ul>

	<div class="container-fluid">
		<div class="row-fluid">
			<div>
			<label>类目Id</label>${bean.catId}
			</div>
			<div>
			<label>类目名称</label>${bean.catName}
			</div>
			
			<div>
			<span style="color:red;font-weight:bold;">${msg}</span>
			</div>
			<div class=""></div>
				<input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;返回列表&nbsp;&nbsp;" onclick="javascript:location.href='ArticleCat?oper=list';"/>
			
			
	
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