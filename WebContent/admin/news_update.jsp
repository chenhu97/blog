<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="__checkLoginAndIsAdmin2.jsp"%>

<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%
	NewsService newsService = new NewsServiceImpl();

	NewsExContService newsExContService = new NewsExContServiceImpl();

	String id = request.getParameter("id");

	// 2) 服务端验证:主键的为空性判断
	if (id == null || id.isEmpty()) {
		out.print("<script>alert('id不能为空。');location.href='news_list.jsp';</script>");
		return;
	}
	// 3) 服务端验证:主键的合法性判断
	Long vId = 0L;
	try {
		vId = Long.parseLong(id);
	} catch (NumberFormatException e) {
		// TODO Auto-generated catch block
		out.print("<script>alert('id不是数字。');location.href='news_list.jsp';</script>");
		return;
	}
	// 4) 业务功能: 从数据库获取数据
	News bean = newsService.load(vId);
	NewsExCont beanEx = newsExContService.load(vId);
	if (bean == null) {
		out.print("<script>alert('不存在对应的数据。');location.href='news_list.jsp';</script>");
		return;
	}
	if (beanEx == null) {
		out.print("<script>alert('不存在对应的数据。');location.href='news_list.jsp';</script>");
		return;
	}
%>
<%
	Object obj_catId = request.getAttribute("catId");

	Object obj_title = request.getAttribute("title");

	Object obj_author = request.getAttribute("author");

	Object obj_summary = request.getAttribute("summary");

	Object obj_content = request.getAttribute("content");

	Object obj_status = request.getAttribute("status");

	Object obj_remark = request.getAttribute("remark");

	Object obj_msg = request.getAttribute("msg");

	Long catId = obj_catId == null ? 0L : (Long) obj_catId;

	String title = obj_title == null ? "" : (String) obj_title;

	String author = obj_author == null ? "" : (String) obj_author;

	String summary = obj_summary == null ? "" : (String) obj_summary;

	String content = obj_status == null ? "" : (String) obj_content;

	String remark = obj_status == null ? "" : (String) obj_remark;

	String status = obj_status == null ? "" : (String) obj_status;

	String msg = obj_msg == null ? "" : (String) obj_msg;

	//如果不存在回显数据，则使用原始的记录中的数据
	catId = catId == 0 ? bean.getCatId() : catId;
	title = title.isEmpty() ? bean.getTitle() : title;
	author = author.isEmpty() ? bean.getAuthor() : author;
	summary = summary.isEmpty() ? bean.getSummary() : summary;
	content = content.isEmpty() ? beanEx.getContent() : content;
	remark = remark.isEmpty() ? bean.getRemark() : remark;
	status = status.isEmpty() ? bean.getStatus() : status;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统后台 - 类目管理 - 修改</title>
<%@include file="_head_link.jsp"%>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/admin/js/plugins/jquery.validate.min.js">
</script>
<script type="text/javascript"
	src="<%=application.getContextPath()%>/static/editor/ckeditor_4.10.1_full/ckeditor/ckeditor.js">
</script>
<script type="text/javascript">
	window.onload=function(){
		CKEDITOR.replace('content',null);
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
<%@include file="_ctx_nav.jsp"%>
<div class="centercontent">

	<div class="pageheader">
		<h1 class="pagetitle">系统后台 - 新闻管理 - 修改</h1>
		<span class="pagedesc">修改新闻数据</span>
	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">

		<form id="myform" action="news_update.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="id" name="id" value="<%=id%>" />
			<p class="RedBold">
				<%=msg%>
			</p>
			<p>
				<label for="catId">新闻类目</label> <span class="field"> <%
 	NewsCatService newsCatService = new NewsCatServiceImpl();
 	List<NewsCat> catList = newsCatService.list();
 %> <select id="catId" name="catId">
						<%
							for (NewsCat item : catList) {
						%>
						<option value="<%=item.getCatId()%>"><%=item.getCatName()%></option>
						<%
							}
						%>
				</select> <script>
			$(function(){
				$("#catId").val("<%=catId %>");
					});
				</script>
				</span>
			</p>
			<p>
				<label for="title">标题</label><span class="field"> <input
					type="text" id="title" name="title" value="<%=title%>" /></span>
			</p>
			<p>
				<label for="author">作者</label><span class="field"> <input
					type="text" id="author" name="author" value="<%=author%>" />
				</span>
			</p>
			<p>
				<label for="summary">摘要</label><span class="field"> <input
					type="text" id="summary" name="summary" value="<%=summary%>" />
				</span>
			</p>
			<p>
				<label for="picPath">图片路径</label> <span class="field"> <input
					type="file" id="picPath" name="picPath" class="mediuminput"
					value="" />
				</span>
				<%
					if (bean.getPicPath() != null) {
						String srcPath = application.getContextPath() + bean.getPicPath();
				%>
				<img style="height: 100px;" src="<%=srcPath%>" />
				<%
					}
				%>
			</p>
			<p>
				<span>内容:</span>
				<textarea id="content" name="content"><%=content%></textarea>
			</p>
			<p>
				<label for="status">状态</label><span class="field"> <input
					type="text" id="status" name="status" value="<%=status%>" /></span>
			</p>
			<p>
				<label for="remark">备注</label><span class="field"> <input
					type="text" id="remark" name="remark" value="<%=remark%>" />
				</span>
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
	<br clear="all" />

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
					}
				},
				messages : {
					catId : {
						required : "新闻名称必填"
					},
					title : {
						required : "新闻描述必填"
					}

				}

			});

		});
	</script>
	<%@include file="_body_end.jsp"%>