<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.blog.bean.*" %>
<%@ page import="edu.blog.service.*"%>
<%@ page import="edu.blog.service.impl.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>易卓博客</title>
<%@include file="_head_link.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<div class="main">
		<%@include file="_body_header.jsp"%>
		
		<div class="body">
			<div class="body_resize">
				<div class="left">
					<img src="<%=application.getContextPath()%>/static/site/images/img_1.jpg" alt="img" width="207" height="210"
						class="floated" />
					
					<p style="font-size:16px;font-weight:bolder;">所有博主信息</p>
					
					<p>
						<%
							BloggerService bloggerService = new BloggerServiceImpl();
							List<Blogger> bloggerList = bloggerService.listByIsEnable(1L);
							request.setAttribute("bloggerList", bloggerList);
						%>
						<c:forEach var="item" items="${bloggerList}">
							<a target="_blank" href="${AppRootPath}/blog/index?f=${item.blogFolder}">${item.nickName}</a>&nbsp;&nbsp;&nbsp;&nbsp;
						</c:forEach>
					</p>
					<a href="#"><img src="<%=application.getContextPath()%>/static/site/images/reading.gif" alt="img" width="118"
						height="26" border="0" /></a>
					<div class="bg"></div>
					<img src="<%=application.getContextPath()%>/static/site/images/img_2.jpg" alt="img" width="207" height="210"
						class="floated" />
					<h2>
						<span>Lorem Ipsum Dolor</span> Sit Amet
					</h2>
					<p>
						<strong>Lorem ipsum dolor sit amet, consectetuer
							adipiscing elit. Donec libero.</strong> Suspendisse bibendum.<br /> Cras
						id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis
						varius lorem, eu posuere nunc justo tempus leo. Donec mattis,
						purus nec placerat bibendum, dui pede condimentum odio, ac blandit
						ante orci ut diam. Cras fringilla magna. Phasellus suscipit, leo a
						pharetra condimentum, lorem tellus eleifend magna, eget fringilla
						velit magna id neque. <a href="#">Curabitur vel urna. In
							tristique orci porttitor ipsum</a>. Lorem ipsum dolor sit amet,
						consectetuer adipiscing elit. Donec libero. Suspendisse bibendum.
						Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus
						turpis varius lorem, eu posuere nunc justo tempus leo.
					</p>
					<a href="#"><img src="<%=application.getContextPath()%>/static/site/images/reading.gif" alt="img" width="118"
						height="26" border="0" /></a>
					<div class="bg"></div>
					<img src="<%=application.getContextPath()%>/static/site/images/img_3.jpg" alt="img" width="207" height="224"
						class="floated" />
					<h2>
						<span>Contrary to</span> popular belief
					</h2>
					<p>
						<strong>Lorem Ipsum is not simply random text. It has
							roots in a piece of classical Latin literature from 45 BC, making
							it over 2000 years old.</strong><br /> Richard McClintock, a Latin
						professor at Hampden-Sydney College in Virginia, looked up one of
						the more obscure Latin words, consectetur, from a Lorem Ipsum
						passage, and going through the cites of the word in classical
						literature, discovered the undoubtable source. Lorem Ipsum comes
						from sections 1.10.32 and 1.10.33 of &quot;de Finibus Bonorum et
						Malorum&quot; (The Extremes of Good and Evil)<a href="#"> by
							Cicero, written in 45 BC. This book is</a> a treatise on the theory
						of ethics, very popular during the Renaissance. The first line of
						Lorem Ipsum, &quot;Lorem ipsum dolor sit amet..&quot;, comes from
						a line in section 1.10.32.
					</p>
					<a href="#"><img src="<%=application.getContextPath()%>/static/site/images/reading.gif" alt="" width="118"
						height="26" border="0" /></a>
					<p>&nbsp;</p>
					<p>&nbsp;</p>
				</div>
				<div class="right">
					<h2>
						<span>Sidebar</span> Menu
					</h2>
					<ul>
						<li><a href="#"> Home</a></li>
						<li><a href="#">TemplateInfo</a></li>
						<li><a href="#">Style Demo</a></li>
						<li><a href="#">Blog</a></li>
						<li><a href="#">Archives</a></li>
						<li><a href="#" title="Website Templates">Web Templates</a></li>
					</ul>
					<h2>
						<span>Sponsors</span>
					</h2>
					<ul class="sponsors">
						<li class="sponsors"><a href="#" title="Website Templates">DreamTemplate</a><br />Over
							6,000+ Premium Web Templates</li>
						<li class="sponsors"><a href="#" title="WordPress Themes">TemplateSOLD</a><br />Premium
							WordPress &amp; Joomla Themes</li>
						<li class="sponsors"><a href="#" title="Affordable Hosting">ImHosted.com</a><br />Affordable
							Web Hosting Provider</li>
						<li class="sponsors"><a href="#"
							title="Free WordPress Themes">FreeThemeLayouts</a><br />Free
							WordPress Themes</li>
						<li class="sponsors"><a href="#" title="Website Builder">Evrsoft</a><br />Website
							Builder Software &amp; Tools</li>
						<li class="sponsors"><a href="#" title="Stock Icons">MyVectorStore</a><br />Royalty
							Free Stock Icons</li>
					</ul>
					<p>&nbsp;</p>
					<%@include file="_body_search_news.jsp"%>
					<div class="clr"></div>
				</div>
				<div class="clr"></div>
			</div>
		</div>
		
		<%@include file="_body_index_bottom.jsp"%>
		
		<%@include file="_body_footer.jsp"%>
	</div>
	<script>
		$(function() {
			$(".header .menu ul li a").removeClass("active");
			$("#home").addClass("active");
		})
	</script>
</body>
</html>
