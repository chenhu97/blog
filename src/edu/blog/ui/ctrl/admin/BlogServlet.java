package edu.blog.ui.ctrl.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.blog.bean.Blogger;
import edu.blog.service.BloggerService;
import edu.blog.service.impl.BloggerServiceImpl;
import edu.uc.ui.ctrl.UIConst;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/admin/Blogger")
public class BlogServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	BloggerService bloggerService = new BloggerServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlogServlet() {
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
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletConfig config = getServletConfig();
		javax.servlet.ServletContext application = request.getServletContext();
		java.io.PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=utf-8" );
		// 对Servlet进行检测
		String toLoginPage = checkLogin(request, response);
		if (toLoginPage != null) {
			response.sendRedirect(toLoginPage);
			return;
		}

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}
		switch (oper) {
		case "passdeal":
			passDeal(request, response);
			break;
		}

	}

	private void passDeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		javax.servlet.http.HttpSession session = request.getSession();
		java.io.PrintWriter out = response.getWriter();
		// 参数处理
		String strId = request.getParameter("id");
		Long id = 0L;
		if (strId != null && strId.trim() != null) {
			id = Long.parseLong(strId);
		}
		// 对象获取
		Blogger bean = bloggerService.load(id);
		if (bean == null) {
			out.print("<script>");
			out.print("alert('id不正确');");
			out.print("history.back()';");
			out.print("</script>");
			return;
		}
		// 通过,则改变值完成审核

		bean.setIsEnable(1L);
		// 修改

		Long num = bloggerService.update(bean);
		if (num > 0) {
			out.print("<script>");
			out.print("alert('审核通过');");
			out.print("location.href='blogger_list.jsp';");
			out.print("</script>");
			out.flush();
		} else {
			out.print("<script>");
			out.print("alert('审核失败');");
			out.print("location.href='blogger_list.jsp';");
			out.print("</script>");
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
