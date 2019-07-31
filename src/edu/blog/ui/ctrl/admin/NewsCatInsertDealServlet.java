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
 * Servlet implementation class NewsCatInsertDealServlet
 */
@WebServlet("/admin/newsCat_insert.do")
public class NewsCatInsertDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public NewsCatService newsCatService = new NewsCatServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsCatInsertDealServlet() {
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

		String catName = request.getParameter("catName");
		String catDesc = request.getParameter("catDesc");

		request.setAttribute("catName", catName);
		request.setAttribute("catDesc", catDesc);

		// 2) 服务端验证: 为空性验证
		String msg = "";

		String toPage = "/admin/newsCat_insert.jsp";

		if (SysFun.isNullOrEmpty(catName)) {
			msg = "类目名称不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(catDesc)) {
			msg = "类目描述不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		// 3) 服务器验证:合法性判断

		// 真正执行添加操作
		NewsCat bean = new NewsCat();
		bean.setCatName(catName);
		bean.setCatDesc(catDesc);

		bean.setCreateOn(new java.util.Date());
		bean.setUpdateOn(new java.util.Date());

		Long result = 0L;
		String msg1 = " ";
		try {
			result = newsCatService.insert(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg1 = e.getMessage();
		}

		// 结果响应
		if (result > 0) {
			out.print("<script>alert('添加成功!');location.href='newsCat_list.jsp';</script>");
			return;
		} else {
			request.setAttribute("msg1", msg1);
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
