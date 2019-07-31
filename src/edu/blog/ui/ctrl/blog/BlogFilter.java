package edu.blog.ui.ctrl.blog;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.blog.bean.ArticleCat;
import edu.blog.bean.Blogger;
import edu.blog.bean.News;
import edu.blog.service.*;
import edu.blog.service.impl.*;

/**
 * Servlet Filter implementation class BlogFilter
 */
@WebFilter("/blog/*")
public class BlogFilter implements Filter {
	BloggerService bloggerService = new BloggerServiceImpl();
	ArticleCatService articleCatService = new ArticleCatServiceImpl();
	NewsService newsService = new NewsServiceImpl();

	/**
	 * Default constructor.
	 */
	public BlogFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rsp;
		String toURL = request.getContextPath() + "/index.jsp";
		// 1)判断f参数,不存在则跳转到主页
		String blogFolder = req.getParameter("f").trim();
		if (blogFolder == null || blogFolder.trim().equals("")) {
			response.sendRedirect(toURL);
			return;
		}
		// 2)根据传入的f参数,取得blogger对象的值
		Blogger blogger = bloggerService.loadByFolder(blogFolder);
		if (blogger == null || blogger.getIsEnable() == 0L) {
			response.sendRedirect(toURL);
			return;
		}
		// 通过为空性验证,将当前博主对象放到作用域中
		request.setAttribute("ViewBlogger", blogger);
		// 3) 取得当前查看博主的所属文章类目
		List<ArticleCat> viewCatList = null;
		viewCatList = articleCatService.listByMemberId(blogger.getMemberId());
		request.setAttribute("ViewCatList", viewCatList);
		// 4) 取得最新的前五篇新闻
		List<News> top5NewsList = newsService.listByCatId_limit_n(null, 5L);
		req.setAttribute("Top5NewsList", top5NewsList);
		// pass the request along the filter chain
		chain.doFilter(req, rsp);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
