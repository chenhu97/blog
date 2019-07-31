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
@WebServlet("/admin/newsCat_delete_ajax.do")
public class NewsCatDeleteAjaxServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public NewsCatService newsCatService = new NewsCatServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsCatDeleteAjaxServlet() {
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
		// response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();
		// 调用BaseServlet方法进行登录验证
		String toLoginURL = checkLogin(request, response);
		if (toLoginURL != null) {
			response.sendRedirect(toLoginURL);
			return;
		}
		// 1) 获取浏览器请求中的要删除的主键值
		String id = request.getParameter("id");
		// 2)服务端验证 主键的为空性判断
		if (id == null || id.isEmpty()) {
			out.print("nook");
			return;
		}
		// 3)服务端验证 主键的合法性
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			out.print("nook");
			return;
		}

		// 4)业务功能 执行真正的删除
		Long result = newsCatService.delete(vId);
		if (result > 0) {
			out.print("ok");
		} else {
			out.print("nook");
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
