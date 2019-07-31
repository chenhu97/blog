<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<h2>
	<span>Search</span>
</h2>
<div class="search">
	<form id="form1" name="form1" method="get" action="news_search.jsp">
		<span> 
		<input name="searchName" type="text" class="keywords" id="textfield"
			maxlength="50" placeholder="Search..." />
		</span> 
		<input name="b" type="image" src="<%=application.getContextPath()%>/static/site/images/search.gif" class="button" />
	</form>
</div>