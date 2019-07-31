package edu.blog.ui.ctrl.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.blog.service.*;
import edu.blog.service.impl.*;

/**
 * Servlet implementation class MemberDeleteDealServlet
 */
@WebServlet("/admin/news_delete.do")
public class NewsDeleteDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public NewsService newsService = new NewsServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsDeleteDealServlet() {
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
		// request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("utf-8");
		// response.setContentType("text/html;charset=utf-8");
		String toLoginURL = checkLogin(request, response);
		if (toLoginURL != null) {
			response.sendRedirect(toLoginURL);
			return;
		}
		java.io.PrintWriter out = response.getWriter();
		// 1) 获取浏览器请求中的要删除的主键值
		String id = request.getParameter("id");
		// 2)服务端验证 主键的为空性判断
		if (id == null || id.isEmpty()) {
			out.println("<script>alert('id不能为空。');location.href='news_list.jsp';</script>");
			return;
		}
		// 3)服务端验证 主键的合法性
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			out.println("<script>alert('id不是数字。');location.href='news_list.jsp';</script>");
			return;
		}

		// 4)业务功能 执行真正的删除
		Long result = newsService.delete(vId);
		if (result > 0) {
			out.println("<script>alert('删除成功。');location.href='news_list.jsp';</script>");
		} else {
			out.println("<script>alert('删除失败。');location.href='news_list.jsp'</script>");
		}

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
