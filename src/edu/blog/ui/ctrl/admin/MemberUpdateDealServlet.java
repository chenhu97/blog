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
 * Servlet implementation class MemberUpdateDealServlet
 */
@WebServlet("/admin/member_update.do")
public class MemberUpdateDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public MemberService memberService = new MemberServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberUpdateDealServlet() {
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

		// 1) 获得请求数据
		String id = request.getParameter("id");

		String userName = request.getParameter("userName");

		String userPass = request.getParameter("userPass");

		String userPass2 = request.getParameter("userPass2");

		String nickName = request.getParameter("nickName");

		String email = request.getParameter("email");

		// 放到request作用域，以便回显,标准做法
		request.setAttribute("id", id);
		request.setAttribute("userName", userName);
		request.setAttribute("nickName", nickName);
		request.setAttribute("email", email);

		// 2) 服务端验证，为空性判断
		String msg = "";
		String toPage = "/admin/member_update.jsp";

		if (SysFun.isNullOrEmpty(id)) {
			msg = "id不能为空";

		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(userName)) {
			msg = "账号不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (!SysFun.isNullOrEmpty(userPass) && !userPass.equals(userPass2)) {
			msg = "如需修改密码,请再次输入密码";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(nickName)) {
			msg = "昵称不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(email)) {
			msg = "Email不能为空";
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
		Member bean = memberService.load(vId);
		// bean.setUserId(vId);
		bean.setUserName(userName);

		if (!SysFun.isNullOrEmpty(userPass)) {
			bean.setUserPass(userPass);
		}
		bean.setNickName(nickName);
		bean.setEmail(email);
		bean.setUpdateOn(new java.util.Date());

		// 5) 业务操作:执行真正的修改
		Long result = 0L;
		try {
			result = memberService.update(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		// 6) 结果响应
		if (result > 0) {
			out.println("<script>alert('修改成功');location.href='member_list.jsp';</script>");
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
