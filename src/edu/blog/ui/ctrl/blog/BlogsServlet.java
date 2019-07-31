package edu.blog.ui.ctrl.blog;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.blog.bean.Article;
import edu.blog.bean.ArticleCat;
import edu.blog.bean.Blogger;
import edu.blog.service.ArticleService;
import edu.blog.service.MemberService;
import edu.blog.service.impl.ArticleServiceImpl;
import edu.blog.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class BlogsServlet
 */
@WebServlet("/blog/blog")
public class BlogsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	ArticleService articleService = new ArticleServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogsServlet() {
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
		Blogger blogger = (Blogger) request.getAttribute("ViewBlogger");
		Long memberId = blogger.getMemberId();
		String title = null;
		String catId = null;
		String folder = null;
		Long pageNum = 1L;
		Long pageSize = 2L;
		Object objViewCatList = request.getAttribute("ViewCatList");
		List<ArticleCat> catList = (List<ArticleCat>) (objViewCatList);
		Map<ArticleCat, List<Article>> DataMap = null;
		DataMap = new TreeMap<ArticleCat, List<Article>>(new Comparator<ArticleCat>() {
			// 重写compare方法
			public int compare(ArticleCat obj1, ArticleCat obj2) {
				return obj1.getCatId().compareTo(obj2.getCatId());
			}
		});
		// 遍历所有类目
		for (ArticleCat tmpCat : catList) {
			// 查找某个类目下的n条数据列表
			List<Article> tmpList = articleService.pagerBySearch(blogger.getMemberId(), tmpCat.getCatId(), title,folder,
					pageNum, pageSize);
			if (tmpList != null&&tmpList.size() > 0) {
				DataMap.put(tmpCat, tmpList);
			}
		}
		request.setAttribute("DataMap", DataMap);
		// 页面转发
		String toPage = "/WEB-INF/blog/blog.jsp";
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
