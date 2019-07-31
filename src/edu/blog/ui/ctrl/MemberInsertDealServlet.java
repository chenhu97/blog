package edu.blog.ui.ctrl;

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
@WebServlet("/member_insert_deal.do")
public class MemberInsertDealServlet extends HttpServlet {
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

		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String userPass2 = request.getParameter("userPass2");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");

		// 回显?目的是什么
		request.setAttribute("userName", userName);
		request.setAttribute("nickName", nickName);
		request.setAttribute("email", email);
		// 为空性判断
		if (SysFun.isNullOrEmpty(userName)) {
			out.print("<script>alert('账号不能为空')</script>");
			return;
		}
		if (SysFun.isNullOrEmpty(userPass)) {
			out.print("<script>alert('密码不能为空')</script>");
			return;
		}
		if (SysFun.isNullOrEmpty(userPass2)) {
			out.print("<script>alert('请再次输入密码');location.href='member_insert.do';</script>");
			return;
		}
		if (!userPass.equals(userPass2)) {
			out.print("<script>alert('两次输入密码不一致');location.href='member_insert.do';</script>");
			return;
		}
		if (SysFun.isNullOrEmpty(nickName)) {
			out.print("<script>alert('昵称不能为空');location.href='member_insert.do';</script>");
			return;
		}

		if (SysFun.isNullOrEmpty(email)) {
			out.print("<script>alert('邮箱不能为空');location.href='member_insert.do';</script>");
			return;
		}
		// 服务器判断:合法性判断

		// 真正执行添加操作
		Member bean = new Member();
		bean.setUserName(userName);
		bean.setUserPass(userPass);
		bean.setNickName(nickName);
		bean.setEmail(email);

		bean.setCreateOn(new java.util.Date());
		bean.setUpdateOn(new java.util.Date());

		Long result = 0L;
		String msg = " ";
		try {
			result = memberService.insert(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getMessage();
		}

		// 结果响应
		if (result > 0) {
			out.print("<script>alert('添加成功!');location.href='member_list.do';</script>");
			return;
		} else {
			out.print("<script>alert('添加失败!" + msg + "');location.href='member_insert.do';</script>");
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
