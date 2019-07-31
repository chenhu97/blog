package edu.blog.ui.ctrl.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.blog.bean.Member;
import edu.blog.service.MemberService;
import edu.blog.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class MemberInsertDealServlet
 */
@WebServlet("/admin/member_insert.do")
public class MemberInsertDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public MemberService memberService = new MemberServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberInsertDealServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		java.io.PrintWriter out = response.getWriter();

		// 调用BaseServlet方法进行登录验证
		String toLoginURL = checkLogin(request, response);
		if (toLoginURL != null) {
			response.sendRedirect(toLoginURL);
			return;
		}

		//String id = request.getParameter("id");
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String userPass2 = request.getParameter("userPass2");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");

		request.setAttribute("userName", userName);
		request.setAttribute("nickName", nickName);
		request.setAttribute("email", email);

		// 2) 服务端验证: 为空性验证
		String msg = "";
		String toPage = "/admin/member_insert.jsp";
		if (SysFun.isNullOrEmpty(userName)) {
			msg = "账号不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(userPass)) {
			msg = "密码不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(userPass2)) {
			msg = "确认密码不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(nickName)) {
			msg = "昵称不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(email)) {
			msg = "Email不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (!userPass.equals(userPass2)) {
			msg = "两次输入的密码不一致!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		// 3) 服务端验证：数据合法性判断
		

		// 4) 真正执行添加操作
		Member bean = new Member();
		bean.setUserName(userName);
		bean.setUserPass(userPass);
		bean.setNickName(nickName);
		bean.setEmail(email);

		bean.setCreateOn(new java.util.Date());
		bean.setUpdateOn(new java.util.Date());

		Long result = 0L;
		String msg1 = "";
		try {
			result = memberService.insert(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg1 = e.getMessage();
		}

		// 5) 结果响应
		if (result > 0) {
			out.print("<script>alert('添加成功!');location.href='member_list.jsp';</script>");
			return;
		} else {
			request.setAttribute("msg1", msg);
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
