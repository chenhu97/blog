<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="__checkLoginAndIsAdmin2.jsp"%>

<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%
	Object obj_catId = request.getAttribute("catId");
	Object obj_title = request.getAttribute("title");
	Object obj_author = request.getAttribute("author");
	Object obj_summary = request.getAttribute("summary");
	Object obj_picPath = request.getAttribute("picPath");
	Object obj_content = request.getAttribute("content");
	Object obj_status = request.getAttribute("status");
	Object obj_remark = request.getAttribute("remark");
	Object obj_msg = request.getAttribute("msg");
	
	Long catId = obj_catId == null ? 0L:(Long) obj_catId;
	String title = obj_catId == null ? "":(String) obj_title;
	String author = obj_catId == null ? "":(String) obj_author;
	String summary = obj_catId == null ? "":(String) obj_summary;
	String picPath = obj_catId == null ? "":(String) obj_picPath;
	String content = obj_catId == null ? "":(String) obj_content;
	String status = obj_catId == null ? "":(String) obj_status;
	String remark = obj_catId == null ? "":(String) obj_remark;
	String msg = obj_catId == null ? "":(String) obj_msg;
	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统后台 - 新闻管理 - 添加</title>

<%@include file="_head_link.jsp"%>

<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery.validate.min.js">
	
</script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/editor/ckeditor_4.10.1_full/ckeditor/ckeditor.js">
	
</script>

<!-- 使用ckeditor步骤3 : 初始化ckeditor的相关配置,为null，这里使用默认配置-->
<script type="text/javascript">
	window.onload = function() {
		CKEDITOR.replace('content', null);
	}
</script>

<style>
.RedBold {
	color: red;
	font-weight: bold;
}
</style>

</head>

<%@include file="_body_start.jsp"%>

<%@include file="_ctx_header.jsp"%>

<%@ include file="_ctx_nav.jsp"%>

<div class="centercontent">

	<div class="pageheader">
		<h1 class="pagetitle">系统后台 - 新闻管理 - 添加</h1>
		<span class="pagedesc">添加新闻数据</span>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">

		<form id="myform" action="news_insert.do" method="post" enctype="multipart/form-data">
			<p class="RedBold">
				<%=msg%>
			</p>

			<p>
				<label for="catId">新闻类目</label> <span class="field"> <%
 	NewsCatService newsCatService = new NewsCatServiceImpl();
 	List<NewsCat> catList = newsCatService.list();
 %> 
 	<select id="catId" name="catId">
					<%
						for (NewsCat item : catList) 
						{
					%>
					<option value="<%=item.getCatId()%>"><%=item.getCatName()%></option>
					<%
					}						%>
	</select>
				</span>
			</p>
			<p>
				<label for="title">标题</label><span class="field"> <input
					type="text" id="title" name="title" class="mediuminput" /></span>
			</p>
			<p>
				<label for="author">作者</label><span class="field"> <input
					type="text" id="author" name="author" class="mediuminput" /></span>
			</p>
			<p>
				<label for="summary">摘要</label><span class="field"> <input
					type="text" id="summary" name="summary" class="mediuminput" /></span>
			</p>
			<p>
				<label for="picPath">图片路径</label><span class="field"> <input
					type="file" id="picPath" name="picPath" class="mediuminput"
					value="" />
				</span>
			</p>
			<p>
				<span>内容:</span>
				<textarea id="content" name="content"></textarea>
			</p>
			<p>
				<label for="status">状态</label><span class="field"> <input
					type="text" id="status" name="status" class="mediuminput" /></span>
			</p>
			<p>
				<label for="remark">备注</label><span class="field"> <input
					type="text" id="remark" name="remark" class="mediuminput" /></span>
			</p>

			<br clear="all" /> <br />
			<p class="stdformbutton">
				<input type="submit" class="submit radius2" value="提交" />
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" class="reset radius2"
					value="重置" />
			</p>
		</form>
	</div>
	<!--contentwrapper-->
	<br clear="all">


	<%@include file="_ctx_footer.jsp"%>

	<script>
		$(function() {
			var validate = $("#myform").validate({
				debug : true,
				errorClass : "RedBold",
				focusInvalid : false,
				onkeyup : false,
				submitHandler : function(form) {
					form.submit();
				},
				rules : {
					catId : {
						required : true
					},
					title : {
						required : true
					},
					author : {
						required : true
					},
					summary : {
						required : true
					},
					content : {
						required : true
					}
				},
				messages : {
					catId : {
						required : "新闻名称必填"
					},
					title : {
						required : "新闻描述必填"
					},
					author : {
						required : "作者信息必填"
					},
					summary : {
						required : "新闻摘要必填"
					},
					content : {
						required : "新闻内容必填"
					}
				}

			});

		});
	</script>
</div>
<%@include file="_body_end.jsp"%>