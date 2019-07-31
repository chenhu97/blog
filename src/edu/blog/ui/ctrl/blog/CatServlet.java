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
 * Servlet implementation class BlogsServlet
 */
@WebServlet("/blog/cat")
public class CatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	ArticleCatService articleCatService = new ArticleCatServiceImpl();
	ArticleService articleService = new ArticleServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatServlet() {
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

	private void defaultView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 1)查看当前的博主对象
		javax.servlet.ServletContext application = request.getServletContext();
		Blogger blogger = (Blogger) request.getAttribute("ViewBlogger");

		Long memberId = blogger.getMemberId();
		String title = null;
		Long catId = null;
		String folder = null;

		// 2)取得类目Id
		String strCatId = request.getParameter("catid");
		catId = SysFun.parseLong(strCatId);
		// 3)取得类目对象
		ArticleCat catBean = articleCatService.load(catId);
		if (catBean == null) {
			// 如果类目对象不存在，则跳转到当前博主的首页
			String toURL = application.getContextPath() + "/index?f=" + blogger.getBlogFolder();
			response.sendRedirect(toURL);
			return;
		}
		request.setAttribute("CatBean", catBean);
		List<Article> vDataList = null;// 取得当前的博主文章列表
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
		// 转发到页面
		String toPage = "/WEB-INF/blog/cat.jsp";
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
