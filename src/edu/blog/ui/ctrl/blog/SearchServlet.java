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

import edu.blog.bean.*;
import edu.blog.service.*;
import edu.blog.service.impl.*;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/blog/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleService articleService = new ArticleServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		javax.servlet.ServletContext application = request.getServletContext();
		Blogger blogger = (Blogger) request.getAttribute("ViewBlogger");
		Long memberId = blogger.getMemberId();

		String title = null;
		Long catId = null;
		String folder = null;

		// 2)取得搜索的关键字
		String searchKey = request.getParameter("s");
		if (SysFun.isNullOrEmpty(searchKey)) {
			request.getRequestDispatcher("/blog/index?f=" + blogger.getBlogFolder()).forward(request, response);
			return;
		}
		request.setAttribute("searchkey", searchKey);
		title = searchKey;

		List<Article> vDataList = null;
		// 分页
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
		Long rowCount = 0L;
		rowCount = articleService.countBySearch(memberId, catId, title, folder);
		pagerItem.changeRowCount(rowCount);
		vDataList = articleService.pagerBySearch(memberId, catId, title, folder, pagerItem.getPageNum(),
				pagerItem.getPageSize());

		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));

		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);
		// 转发页面
		String toPage = "/WEB-INF/blog/search.jsp";
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
