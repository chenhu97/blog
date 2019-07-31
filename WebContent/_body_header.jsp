<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="header">
	<div class="header_resize">
		<div class="menu">
			<ul>
				<li><a id="home" href="index.jsp" class="active"><span>首页</span></a></li>
				<li><a id="news" href="news.jsp"><span>新闻</span></a></li>
				<li><a id="about" href="about.jsp"><span>关于我们</span></a></li>
				<li><a id="contact" href="contact.jsp"><span>联系我们</span></a></li>
			</ul>
		</div>
		<div class="twitter"> <img src="<%=application.getContextPath()%>/static/site/images/twitter.gif" alt="img" width="47" height="55" />
        <p><a href="#">TWITTER</a><br />
          Receive updates as soon as they are posted.</p>
      </div>
      <div class="logo"><h1><a href="index.html"><span>Best</span> and necessary<br /><small>Runs faster. Costs less. And never breaks</small></a></h1></div>
		<div class="clr"></div>
	</div>
</div>