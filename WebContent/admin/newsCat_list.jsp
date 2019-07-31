<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="__checkLoginAndIsAdmin2.jsp"%>

<%@ page import="edu.blog.bean.*"%>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.liuvei.common.*"%>

<%
	
NewsCatService newscatService = new NewsCatServiceImpl();
	List<NewsCat> vDataList = null;
%>
<%
	//获取搜索参数
	String searchName = request.getParameter("searchName");
	searchName = searchName == null ? "": searchName;//为null时，则赋值为""
%>


<%
	//分页步骤1. 创建PaperItem对象，处理url传过来的pageSize和pageindex
	PagerItem pagerItem = new PagerItem();
	pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
	pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
	if(SysFun.isNullOrEmpty(searchName)){
		//为空时采用普通分页方法
		//分页步骤2.1 根据条件，查找符合条件的所有记录数  *****count()要根据实际换成其他方法pagerItem
		Long rowCount = newscatService.count();
		//分页步骤2.2 将记录的数据传给pagerItem,便于进行分页的各类计算
		pagerItem.changeRowCount(rowCount);
		//分页步骤2.3 从数据库中获取指定分页数据  方法根据实际改变
		vDataList = newscatService.pager(pagerItem.getPageNum(),pagerItem.getPageSize());
		//分页步骤2.4 设置页面的跳转url 
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
	}else{
		//不为空，则采用搜索分页方法
		Long rowCount = newscatService.countByName(searchName);
		
		pagerItem.changeRowCount(rowCount);
		
		vDataList = newscatService.pagerByName(searchName, pagerItem.getPageNum(),pagerItem.getPageSize());
		
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
	}
	
	
	//分页步骤3 将分页对象和数据列表放在作用域，以便页面可以访问
	request.setAttribute("pagerItem", pagerItem);
	request.setAttribute("DataList", vDataList);
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title>系统后台 - 类目管理 - 列表</title>
<%@include file="_head_link.jsp"%>
</head>
<%@include file="_body_start.jsp"%>

<%@include file="_ctx_header.jsp"%>

<%@include file="_ctx_nav.jsp"%>

<div class="centercontent">

	<div class="pageheader">
		<h1 class="pagetitle">系统后台 - 类目管理 - 列表</h1>
		<span class="pagedesc">列出所有类目数据</span>

	</div>
	<!--pageheader-->

	<div id="contentwrapper" class="contentwrapper">
		<form action='newsCat_list.jsp' method='get' id='listform'>
			<span>关键字</span>
			<input type= 'search' name='searchName' id='searchName' placeholder='请输入要搜索的关键字' />
			<input type='submit' value='搜索'/>
			<input type='button' onclick="javascript:location.href='newsCat_list.jsp'" value='清空'/>
		
		</form>
		<button onclick="javascript:location.href='newsCat_insert.jsp';">添加</button>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<button onclick="javascript:datadel();">批量删除</button>

		<table id="dataList" cellpadding="0" cellspacing="0" border="0"
			class="stdtable">
			<thead>
				<tr>
					<th class="head1"><input type="checkbox" id="checkall" /></th>
					<th class="head0">序号</th>
					<th class="head1">Id</th>
					<th class="head0">类目名称</th>
					<th class="head1">类目描述</th>
					<th class="head0">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 1;
					for (NewsCat item : vDataList) {
				%>
				<tr>
					<td><input type="checkbox" value="<%=item.getCatId()%>" /></td>
					<td><%=i%></td>
					<td><%=item.getCatId()%></td>
					<td><%=item.getCatName()%></td>
					<td class="center"><%=item.getCatDesc()%></td>
					<td class="center">
					    <a href="newsCat_delete.do?id=<%=item.getCatId()%>">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;

						<a href="newsCat_update.jsp?id=<%=item.getCatId()%>">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;

						<a href="newsCat_detail.jsp?id=<%=item.getCatId()%>">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<%
					i++;
					}
				%>
			</tbody>
		</table>
		<jsp:include page="__pager.jsp" flush="true"></jsp:include>
	</div>
	<!--contentwrapper-->


	<%@include file="_ctx_footer.jsp"%>

	<script>
		$(function() {
			//全选与反选功能
			jQuery("#checkall").change(function() {
								//选中[其他复选框],排除全选框
								var $otherCheckList = jQuery("#dataList input:checkbox:not('#checkall')");
								//取得全选框当前的选中状态
								var checkAllState = jQuery("#checkall").prop(
										"checked");
								//将全选框的状态附加给其他复选框
								$otherCheckList.prop("checked", checkAllState);
							});
			jQuery("#searchName").val("<%=searchName%>");
		});

		function datadel() {
			if (confirm("真的要删除吗？")) {

				var num = 0;
				var total = 0;
				var obj = null;
				var $otherCheckList = jQuery("input:checkbox:checked:not('#checkall')");

				//迭代所有已选中框
				$otherCheckList.each(function() {
					obj = this;
					var id = jQuery(this).val();
					if (id != null && id != "" && id != "0") {
						total++;
						jQuery.ajax({
							type : 'POST',
							url : "newsCat_delete_ajax.do",
							async : false,
							data : {
								"id" : id
							},
							success : function(data) {
								if (data == "ok") {
									num++;
								} else {
									alert("删除失败！");
								}
							},
							error : function(data) {
								alert("Error!");
							},
						});
					}
				});
				alert('要删除' + total + '行记录,成功删除' + num + '行记录。');
				location.reload();
			}
		}
	</script>
	<%@include file="_body_end.jsp"%>