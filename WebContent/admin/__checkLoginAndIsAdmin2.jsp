
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ page import="edu.uc.ui.ctrl.*"%>
<%@ page import="edu.blog.bean.*" %>
<%@ page import="edu.blog.ui.ctrl.*" %>

<c:choose>
	<c:when test="${empty BG_LOGINUSER_KEY}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:when>
	<c:otherwise>
		<c:if test="${not BG_LOGINUSER_KEY.userName.equals('admin')}">
			<c:remove var="BG_LOGINUSER_KEY"></c:remove>
			<c:redirect url="login.jsp"></c:redirect>
		</c:if> 
	</c:otherwise>
</c:choose>







