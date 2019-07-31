<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>易卓博客 - 会员系统 - 类目管理</title>
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
		<h1 class="page-title">类目管理</h1>
	</div>

	<ul class="breadcrumb">
		<li><a href="Main">会员系统</a> 
		<span class="divider">/</span></li>
		<li class="active">类目管理</li>
	</ul>

	<div class="container-fluid">
		<div class="row-fluid">
			<form method="get" action="ArticleCat" id="listform">
				<div class="text-c">
					<input type="hidden" name="oper" value="listdeal" /> <input
						type="text" style="width: 250px;" placeholder="请输入类目名称" id=""
						name="catName" value="${catName}" />
					<button type="submit" class="btn btn-success radius" id="search">搜索类目</button>
					<button type="reset" class="btn btn-success radius"
						onclick="javascript:location.href='ArticleCat?oper=list';">清除</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="l">
					<button onclick="javascript:datadel()"
						class="btn btn-primary radius">批量删除</button>
					<button
						onclick="javascript:location.href='ArticleCat?oper=insert';"
						class="btn btn-primary radius">添加类目</button>
				</span>
			</div>
			<div class="well">
				<table class="table" id="datalist">
					<thead>
						<tr>
							<th width="50"><input type="checkbox" name="checkAll"
								id="checkAll" value="" /></th>
							<th width="100">类目Id</th>
							<th width="200">类目名称</th>
							<th width="200">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${DataList}">
							<tr>
								<td><input type="checkbox" name="checkBox"
									value="${bean.catId}" /></td>
								<td>${bean.catId}</td>
								<td>${bean.catName}</td>
								<td><a href="ArticleCat?oper=update&id=${bean.catId}">
										<i class="icon-pencil">编辑</i>
								</a> <a href="ArticleCat?oper=detail&id=${bean.catId}"> <i
										class="icon-detail">查看</i>
								</a> <a href="javascript:" onclick="item_del(this,${bean.catId})">
										<i class="icon-remove">删除</i>
								</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<jsp:include page="__pager.jsp" flush="true"></jsp:include>
		</div>
		<jsp:include page="_ctx_footer.jsp"></jsp:include>
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
<script>
	
		function item_del(obj,id){
			if(confirm('确定要删除吗?')){
				$.ajax({
					type : 'POST',
					url : 'ArticleCat?oper=deleteDeal&id=' + id,
					success : function(data){
						if(data=='ok'){
							$(obj).parents("tr").remove();
							alert('已删除!');
						}else{
							alert('删除失败!');
						}
					},
					error : function(data){
						
					},
				});	
			}
		};
		$(function(){
			
			$("#datalist #checkAll").change(function(){
				var $otherCheckList = $("#datalist input:checkbox:not('checkAll')");
				
				var checkAllState = $('#checkAll').prop("checked");
				$otherCheckList.prop("checked",checkAllState);
			})
		});
		function datadel(){
			if(confirm('确定要删除吗')){
				var num = 0 ;//成功行数
				var total = 0;//删除总行数
				var obj = null;//当前对象
				var id = 0;//主键
				$("#datalist input[type=checkbox]:checked").each(function(index){
					obj = this;
					id =$(this).val();
					if(id != null && id != "" && id!="0"){
						total++;
						$.ajax({
							type : 'POST',
							url : 'ArticleCat',
							async:false,
							data : {"oper":"deleteDeal","id":id},
							success : function(data){
								if(data == 'ok'){
									$(obj).parents("tr").remove();
									num++;
								}else{
									//alert('删除失败');
								}
							},
							error: function(data){
						
							},
						});
					}
				});
				alert('要删除' + total +'行记录,成功删除' + num + "行记录。");
			}
		}
</script>
<jsp:include page="_body_end.jsp"></jsp:include>