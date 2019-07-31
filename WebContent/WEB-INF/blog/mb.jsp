<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--

Released for free under a Creative Commons Attribution 3.0 License
-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>${ViewBlogger.blogTitle}模板页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<jsp:include page="_head_link.jsp"></jsp:include>

<!-- CuFon ends -->
</head>
<body>
	<jsp:include page="_layout_header.jsp"></jsp:include>
	<div class="main">

		<jsp:include page="_ctx_header.jsp"></jsp:include>

		<div class="content">
			<!-- 内容开始 -->
			<div class="mainbar">
				<div class="article">
					<h2>
						<span>A Blog</span> Entry
					</h2>
					<div class="clr"></div>
					<p>
						Posted by <a href="#">Owner</a> <span>&nbsp;&bull;&nbsp;</span>
						Filed under <a href="#">templates</a>, <a href="#">internet</a>
					</p>
					<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
						Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt,
						orci ac convallis aliquam, lectus turpis varius lorem, eu posuere
						nunc justo tempus leo. Donec mattis, purus nec placerat bibendum,
						dui pede condimentum odio, ac blandit ante orci ut diam. Cras
						fringilla magna. Phasellus suscipit, leo a pharetra condimentum,
						lorem tellus eleifend magna, eget fringilla velit magna id neque.
						Curabitur vel urna. In tristique orci porttitor ipsum. Lorem ipsum
						dolor sit amet, consectetuer adipiscing elit. Donec libero.
						Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac
						convallis aliquam, lectus turpis varius lorem, eu posuere nunc
						justo tempus leo.</p>
					<p>
						Tagged: <a href="#">orci</a>, <a href="#">lectus</a>, <a href="#">varius</a>,
						<a href="#">turpis</a>
					</p>
					<p>
						<a href="#"><strong>Comments (3)</strong></a> <span>&nbsp;&bull;&nbsp;</span>
						May 27, 2010 <span>&nbsp;&bull;&nbsp;</span> <a href="#"><strong>Edit</strong></a>
					</p>
				</div>
				<div class="article">
					<h2>
						<span>3</span> Responses
					</h2>
					<div class="clr"></div>
					<div class="comment">
						<a href="#"><img src="images/userpic.gif" width="40"
							height="40" alt="user" class="userpic" /></a>
						<p>
							<a href="#">admin</a> Says:<br />April 20th, 2009 at 2:17 pm
						</p>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Donec libero. Suspendisse bibendum.</p>
					</div>
					<div class="comment">
						<a href="#"><img src="images/userpic.gif" width="40"
							height="40" alt="user" class="userpic" /></a>
						<p>
							<a href="#">Owner</a> Says:<br />April 20th, 2009 at 3:21 pm
						</p>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Donec libero. Suspendisse bibendum. Cras id urna. Morbi
							tincidunt, orci ac convallis aliquam, lectus turpis varius lorem,
							eu posuere nunc justo tempus leo.</p>
					</div>
					<div class="comment">
						<a href="#"><img src="images/userpic.gif" width="40"
							height="40" alt="user" class="userpic" /></a>
						<p>
							<a href="#">admin</a> Says:<br />April 20th, 2009 at 2:17 pm
						</p>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Donec libero. Suspendisse bibendum.</p>
					</div>
				</div>
				<div class="article">
					<h2>
						<span>Leave a</span> Reply
					</h2>
					<div class="clr"></div>
					<form action="#" method="post" id="leavereply">
						<ol>
							<li><label for="name">Name (required)</label> <input
								id="name" name="name" class="text" /></li>
							<li><label for="email">Email Address (required)</label> <input
								id="email" name="email" class="text" /></li>
							<li><label for="website">Website</label> <input id="website"
								name="website" class="text" /></li>
							<li><label for="message">Your Message</label> <textarea
									id="message" name="message" rows="8" cols="50"></textarea></li>
							<li><input type="image" name="imageField" id="imageField"
								src="images/submit.gif" class="send" />
								<div class="clr"></div></li>
						</ol>
					</form>
				</div>
			</div>
			<!-- 内容结束 -->
			<jsp:include page="_ctx_nav.jsp"></jsp:include>
			<div class="clr"></div>
		</div>

		<jsp:include page="_ctx_footer.jsp"></jsp:include>

	</div>
	<jsp:include page="_layout_footer.jsp"></jsp:include>
	<script>
		$(function() {
			$("#index").addClass("active");
		})
	</script>
</body>
</html>