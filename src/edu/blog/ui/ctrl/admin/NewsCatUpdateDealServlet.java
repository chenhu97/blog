package edu.blog.ui.ctrl.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.blog.bean.*;
import edu.blog.service.*;
import edu.blog.service.impl.*;

/**
 * Servlet implementation class MemberUpdateDealServlet
 */
@WebServlet("/admin/newsCat_update.do")
public class NewsCatUpdateDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public NewsCatService newsCatService = new NewsCatServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsCatUpdateDealServlet() {
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
		String toLoginURL = checkLogin(request, response);
		if (toLoginURL != null) {
			response.sendRedirect(toLoginURL);
			return;
		}
		// 1) 获得请求数据
		String id = request.getParameter("id");

		String catName = request.getParameter("catName");

		String catDesc = request.getParameter("catDesc");

		String status = request.getParameter("status");

		// 放到request作用域，以便回显,标准做法
		request.setAttribute("id", id);
		request.setAttribute("catName", catName);
		request.setAttribute("catDesc", catDesc);
		request.setAttribute("status", status);

		// 2) 服务端验证，为空性判断
		String msg = "";
		String toPage = "/admin/newsCat_update.jsp";

		if (SysFun.isNullOrEmpty(id)) {
			msg = "id不能为空";

		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(catName)) {

			msg = "类目名称不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(catDesc)) {
			msg = "类目描述不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		// 3) 服务端验证：数据合法性判断
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			msg = "id不是数字";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		// 4) 业务操作
		NewsCat bean = newsCatService.load(vId);
		bean.setCatName(catName);
		bean.setCatDesc(catDesc);
		bean.setStatus(status);
		bean.setUpdateOn(new java.util.Date());

		// 5) 业务操作:执行真正的修改
		Long result = 0L;
		try {
			result = newsCatService.update(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		// 6) 结果响应
		if (result > 0) {
			out.print("<script>alert('修改成功');location.href='newsCat_list.jsp';</script>");
		} else {
			msg = "修改失败" + msg;
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
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
