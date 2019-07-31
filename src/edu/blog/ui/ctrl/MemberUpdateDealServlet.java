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
 * Servlet implementation class MemberUpdateDealServlet
 */
@WebServlet("/member_update_deal.do")
public class MemberUpdateDealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MemberService memberService = new MemberServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberUpdateDealServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private StringBuilder alertGo(String msg, String url) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append("alert('" + msg + "');");
		sb.append("location.href='" + url + "';");
		sb.append("</script>");
		return sb;
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
		// 1) 获得请求数据
		String id = request.getParameter("userId");

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
		String updateUrl = "member_update.do?id=" + id;

		if (SysFun.isNullOrEmpty(id)) {
			out.print(alertGo("id不能为空", "member_list.do"));
			return;
		}
		if (SysFun.isNullOrEmpty(userName)) {
			out.print(alertGo("账号不能为空", updateUrl));
			return;
		}
		if (!SysFun.isNullOrEmpty(userPass) && !userPass.equals(userPass2)) {
			out.print(alertGo("密码不能为空", updateUrl));
			return;
		}
		// 3) 服务端验证：数据合法性判断
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			out.print(alertGo("id不是数字。", "member_list.do"));
			return;
		}
		// 4) 业务操作
		Member bean = memberService.load(vId);
		bean.setUserName(userName);
		
		if (!SysFun.isNullOrEmpty(userPass)) {
			bean.setUserPass(userPass);
		}
		bean.setNickName(nickName);
		bean.setEmail(email);

		bean.setUpdateOn(new java.util.Date());
		// 5) 业务操作:执行真正的修改
		Long result = 0L;
		String msg = " ";
		try {
			result = memberService.update(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		// 6) 结果响应
		if (result > 0) {
			out.print(alertGo("修改成功！", "member_list.do"));
		} else {
			System.out.println(msg);
			msg = msg.replace("\'", "");
			out.print(alertGo("修改失败！" + msg, updateUrl));
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
