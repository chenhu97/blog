package edu.blog.ui.ctrl.blog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;

import edu.blog.bean.Article;
import edu.blog.bean.Blogger;
import edu.blog.service.ArticleService;
import edu.blog.service.MemberService;
import edu.blog.service.impl.ArticleServiceImpl;
import edu.blog.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/blog/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	ArticleService articleService = new ArticleServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
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
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletConfig config = getServletConfig();
		javax.servlet.ServletContext application = request.getServletContext();
		java.io.PrintWriter out = response.getWriter();
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

	private void defaultView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 取得当前查看的博主对象
		Blogger blogger = (Blogger) request.getAttribute("ViewBlogger");
		// 取得当前博主对象的所有文章列表
		List<Article> vDataList = null;

		// 分页
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
		Long rowCount = 0L;
		rowCount = articleService.countBySearch(blogger.getMemberId(), null, null, null);
		pagerItem.changeRowCount(rowCount);
		vDataList = articleService.pagerBySearch(blogger.getMemberId(), null, null, null, pagerItem.getPageNum(),
				pagerItem.getPageSize());

		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);
		// 转发页面
		String toPage = "/WEB-INF/blog/index.jsp";
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
