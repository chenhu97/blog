package edu.blog.ui.ctrl.member;

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
import edu.blog.bean.ArticleCat;
import edu.blog.bean.Member;
import edu.blog.service.ArticleCatService;
import edu.blog.service.ArticleService;
import edu.blog.service.impl.ArticleCatServiceImpl;
import edu.blog.service.impl.ArticleServiceImpl;
import edu.uc.ui.ctrl.UIConst;

/**
 * Servlet implementation class ArticleServlet
 */
@WebServlet("/member/Article")
public class ArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleService articleService = new ArticleServiceImpl();
	ArticleCatService articleCatService = new ArticleCatServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Member getLoginUser(HttpServletRequest request) {
		javax.servlet.http.HttpSession session = request.getSession();
		Member bean = (Member) session.getAttribute(UIConst.FG_LOGINUSER_KEY);
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = getServletContext();
		javax.servlet.ServletConfig config = getServletConfig();
		java.io.PrintWriter out = response.getWriter();

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}
		switch (oper) {
		case "list":
			listView(request, response);
			break;
		case "listdeal":
			listDeal(request, response);
			break;
		case "insert":
			insertView(request, response);
			break;
		case "insertdeal":
			insertDeal(request, response);
			break;
		case "update":
			updateView(request, response);
			break;
		case "updatedeal":
			updateDeal(request, response);
			break;
		case "deletedeal":
			deleteDeal(request, response);
			break;
		case "detail":
			detailView(request, response);
			break;
		default:
			System.out.println("操作不存在");
			break;
		}
	}

	private void listView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得当前登录用户Id
		Long memberId = getLoginUser(request).getUserId();

		List<Article> vDataList = null;
		// 分页
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
		Long rowCount = 0L;
		rowCount = articleService.countBySearch(memberId, null, null, null);
		pagerItem.changeRowCount(rowCount);
		vDataList = articleService.pagerBySearch(memberId, null, null, null, pagerItem.getPageNum(),
				pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);
		// 转发页面
		String toPage = "/WEB-INF/member/Article_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	private void listDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得当前登录用户Id
		Long memberId = getLoginUser(request).getUserId();
		String title = request.getParameter("title");
		request.setAttribute("title", title);
		List<Article> vDataList = null;
		// 分页
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
		Long rowCount = 0L;
		rowCount = articleService.countBySearch(memberId, null, title, null);
		pagerItem.changeRowCount(rowCount);
		vDataList = articleService.pagerBySearch(memberId, null, title,null, pagerItem.getPageNum(),
				pagerItem.getPageSize());
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);

		String toPage = "/WEB-INF/member/Article_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	private void insertView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 获得当前登录用户Id
		Long memberId = getLoginUser(request).getUserId();
		// 下拉类目
		List<ArticleCat> catList = articleCatService.listByMemberId(memberId);
		request.setAttribute("CatList", catList);

		String toPage = "/WEB-INF/member/Article_insert.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
	}

	private void insertDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		// 获得当前登录用户Id
		Long memberId = getLoginUser(request).getUserId();

		// 路径
		String folder = request.getParameter("folder");
		request.setAttribute("folder", folder);
		// 内容
		String content = request.getParameter("content");
		request.setAttribute("content", content);
		// 类目Id
		String catId = request.getParameter("catId");
		request.setAttribute("catId", catId);
		// 标题
		String title = request.getParameter("title");
		request.setAttribute("title", title);
		// 更新时间
		String updateOn = request.getParameter("updateOn");
		request.setAttribute("updateOn", updateOn);
		// 发布时间
		// Date publishOn = request.getParameter("publishOn");
		// request.setAttribute("publishOn", publishOn);
		// 昵称
		String nickName = getLoginUser(request).getNickName();

		// 服务端验证

		String vMsg = "";
		if (SysFun.isNullOrEmpty(catId)) {
			vMsg += "请选择一个类目";
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			insertView(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(title)) {
			vMsg += "标题不能为空";
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			insertView(request, response);
			return;
		}
		// if (SysFun.isNullOrEmpty(folder)) {
		// vMsg += "路径不能为空";
		// }
		// if (!SysFun.isNullOrEmpty(vMsg)) {
		// request.setAttribute("msg", vMsg);
		// insertView(request, response);
		// return;
		// }

		if (SysFun.isNullOrEmpty(content)) {
			vMsg += "内容不能为空";
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			insertView(request, response);
			return;
		}

		// 执行业务
		Article bean = new Article();
		bean.setTitle(title);
		bean.setFolder("");
		bean.setCatId(SysFun.parseLong(catId));
		bean.setMemberId(memberId);
		bean.setNickName(nickName);
		bean.setContent(content);
		bean.setPublishOn(new java.util.Date());
		// bean.setUpdateOn();
		Long result = 0L;
		try {
			result = articleService.insert(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vMsg = "添加失败" + e.getMessage();
		}
		if (result > 0) {
			out.println("<script>");
			out.println("alert('添加成功');");
			out.println("location.href='Article?oper=list';");
			out.println("</script>");
		} else {
			request.setAttribute("msg", vMsg);
			insertView(request, response);
		}

	}

	private void updateView(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		//要修改的文章的主键
		String vId = request.getParameter("id");
		// 获得当前登录用户Id
		Long memberId = getLoginUser(request).getUserId();
		// 下拉类目
		List<ArticleCat> catList = articleCatService.listByMemberId(memberId);
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			Article bean = articleService.load(iId);

			if (bean != null) {
				if (bean.getMemberId() != getLoginUser(request).getUserId()) {
					out.print("<script>");
					out.print("alert('Naive！');");
					out.print("location.href='Article?oper=list';");
					out.print("</script>");
					return;
				}
				request.setAttribute("catList", catList);
				request.setAttribute("articleId", bean.getArticleId());
				request.setAttribute("title", bean.getTitle());
				request.setAttribute("folder", bean.getFolder());
				request.setAttribute("catId", bean.getCatId());
				request.setAttribute("catName", bean.getCatName());
				request.setAttribute("publishOn", bean.getPublishOn());
				request.setAttribute("updateOn", bean.getUpdateOn());
				request.setAttribute("content", bean.getContent());
				String toPage = "/WEB-INF/member/Article_update.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
		}
		out.print("<script>");
		out.print("alert('数据不存在');");
		out.print("location.href='Article?oper=list';");
		out.print("</script>");
	}

	private void updateDeal(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		// 获得当前登录用户Id
		Long memberId = getLoginUser(request).getUserId();
		request.setAttribute("memberId", memberId);

		String nickName = getLoginUser(request).getNickName();
		request.setAttribute("nickName", nickName);

		Long articleId = Long.parseLong(request.getParameter("articleId"));
		request.setAttribute("articleId", articleId);
		Long catId = Long.parseLong(request.getParameter("catId"));
		request.setAttribute("catId", catId);

		String catName = request.getParameter("catName");
		request.setAttribute("catName", catName);

		String title = request.getParameter("title");
		request.setAttribute("title", title);
		// String folder = request.getParameter("folder");
		// request.setAttribute("folder", folder);
		String content = request.getParameter("content");
		request.setAttribute("content", content);
		String publishOn = request.getParameter("publishOn");
		request.setAttribute("publishOn", publishOn);
		String updateOn = request.getParameter("updateOn");
		request.setAttribute("updateOn", updateOn);

		// 服务端验证
		String vMsg = "";
		if (SysFun.isNullOrEmpty(title)) {
			vMsg += "文章标题不能为空";
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
			return;
		}
		// if (SysFun.isNullOrEmpty(folder)) {
		// vMsg += "路径不能为空";
		// }
		// if (!SysFun.isNullOrEmpty(vMsg)) {
		// request.setAttribute("msg", vMsg);
		// updateView(request, response);
		// return;
		// }
		if (SysFun.isNullOrEmpty(content)) {
			vMsg += "文章内容不能为空";
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
			return;
		}
		// 执行业务
		Article bean = null;
		if (SysFun.isNullOrEmpty(articleId.toString())) {
			vMsg += "没有指定主键";
		} else {
			bean = articleService.load(articleId);
			if (bean == null) {
				vMsg += "数据不存在";
			} else {
				if (bean.getMemberId() != getLoginUser(request).getUserId()) {
					out.print("<script>");
					out.print("alert('Naive！');");
					out.print("location.href='Article?oper=list';");
					out.print("</script>");
					return;
				}
			}
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
			return;
		}
		Long result = 0L;
		// bean.setArticleId(articleId);
		bean.setTitle(title);
		bean.setCatId(catId);
		bean.setCatName(catName);
		bean.setMemberId(memberId);
		bean.setNickName(nickName);
		bean.setFolder("");
		bean.setContent(content);
		bean.setUpdateOn(new java.util.Date());
		// 两个日期
		try {
			result = articleService.update(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vMsg += "修改失败" + e.getMessage();
		}
		if (result > 0) {
			out.print("<script>");
			out.print("alert('修改成功!');");
			out.print("location.href='Article?oper=list';");
			out.print("</script>");
		} else {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
		}

	}

	private void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			Article article = articleService.load(iId);
			if (article == null) {
				out.print("nook");
				return;
			}
			Long result = articleService.delete(iId);
			if (result > 0) {
				out.print("ok");
				return;
			}
		}
		out.print("nook");

	}

	private void detailView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			Article bean = articleService.loadWithCatName(iId);
			if (bean != null) {
				if (bean.getMemberId() != getLoginUser(request).getUserId()) {
					out.print("<script>");
					out.print("alert('Naive！');");
					out.print("location.href='Article?oper=list';");
					out.print("</script>");
					return;
				}
				request.setAttribute("bean", bean);
				String toPage = "/WEB-INF/member/Article_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
			}
		}
		out.print("<script>");
		out.print("alert('数据不存在');");
		out.print("location.href='Article?oper=list';");
		out.print("</script>");
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
