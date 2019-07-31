package edu.blog.ui.ctrl.blog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.blog.bean.Article;
import edu.blog.bean.Blogger;
import edu.blog.service.ArticleService;
import edu.blog.service.impl.ArticleServiceImpl;

/**
 * Servlet implementation class DetailServlet
 */
@WebServlet("/blog/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleService articleService = new ArticleServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}
		switch (oper) {
		default:
			defaultView(request, response);
			break;
		}
	}

	private void defaultView(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		javax.servlet.ServletContext application = request.getServletContext();
		// 1)获得当前查看的博主对象
		Blogger blogger = (Blogger) request.getAttribute("ViewBlogger");
		// 取得文章Id
		String strArticleId = request.getParameter("articleid");
		Long articleId = SysFun.parseLong(strArticleId);
		// 3)取得文章对象
		Article bean = articleService.load(articleId);
		if (bean == null) {
			// 文章对象不存在,则跳转到当前博主的首页
			String toURL = application.getContextPath() + "/index?f=" + blogger.getBlogFolder();
			response.sendRedirect(toURL);
			return;
		}
		request.setAttribute("articleBean", bean);
		// 转发到页面
		String toPage = "/WEB-INF/blog/detail.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
