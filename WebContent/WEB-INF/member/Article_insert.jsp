<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易卓博客 - 会员系统 - 文章管理 - 添加文章</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="__head_link.jsp"></jsp:include>
<script type="text/javascript"
	src="${AppRootPath}/static/editor/ckeditor_4.10.1_full/ckeditor/ckeditor.js">
</script>

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
		<h1 class="page-title">添加文章</h1>
	</div>
	<ul class="breadcrumb">
		<li><a href="Main">会员系统</a> <span class="divider">/</span></li>
		<li><a href="Article?oper=list">文章管理</a> <span class="divider">/</span></li>
		<li class="active">文章添加</li>
	</ul>

	<div class="container-fluid">
		<div class="row-fluid">
			<form id="tab" action="Article" method="post">
				<input type="hidden" name="oper" value="insertDeal" />
				<div>
					<label>类目名称</label>
					<!-- 下拉 -->
					<select id="catId" name="catId">
						<option value="">请选择类目</option>
						<c:forEach var="bean" items="${CatList}">
							<option value="${bean.catId}">${bean.catName}</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label>文章标题</label> <input type="text" name="title"
						value="${title}" class="input-xlarge" />
				</div>
				<!-- 
				<div>
					<label>路径</label> <input type="file" name="folder"
						value="${folder}" class="input-xlarge" />
				</div>
				 -->
				<p>
					<span>内容:</span>
					<textarea id="content" name="content"></textarea>
				</p>
				<div>
					<span style="color: red; font-weight: bold;">${msg}</span>
				</div>
				<div class="">
					<input class="btn btn-primary radius" type="submit"
						value="&nbsp;&nbsp;提交&nbsp;&nbsp;" />
				</div>
			</form>
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
<script type="text/javascript">
	window.onload = function() {
		CKEDITOR.replace('content', null);
	}
</script>
<jsp:include page="_body_end.jsp"></jsp:include>