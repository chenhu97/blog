package edu.blog.ui.ctrl.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.blog.bean.BloggerEx;
import edu.blog.bean.Member;
import edu.blog.service.BloggerExService;
import edu.blog.service.BloggerService;
import edu.blog.service.MemberService;
import edu.blog.service.impl.BloggerExServiceImpl;
import edu.blog.service.impl.BloggerServiceImpl;
import edu.blog.service.impl.MemberServiceImpl;
import edu.uc.ui.ctrl.UIConst;

/**
 * Servlet implementation class BloggerServlet
 */
@WebServlet("/member/BloggerEx_contact")
public class BloggerExContactServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6985088484021258212L;
	MemberService memberService = new MemberServiceImpl();
	BloggerService bloggerService = new BloggerServiceImpl();
	BloggerExService bloggerExService = new BloggerExServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BloggerExContactServlet() {
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
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = request.getServletContext();
		javax.servlet.ServletConfig config = getServletConfig();
		java.io.PrintWriter out = response.getWriter();

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}
		switch (oper) {
		case "defaultdeal":
			defaultDeal(request, response);
			break;
		default:
			defaultView(request, response);
			break;
		}

	}

	private void defaultDeal(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		javax.servlet.ServletConfig config = getServletConfig();
		javax.servlet.ServletContext application = request.getServletContext();
		javax.servlet.http.HttpSession session = request.getSession();
		response.setContentType("text/html;charset=utf-8");

		// 获得参数
		String content = request.getParameter("content");
		// 回显
		request.setAttribute("content", content);
		// 服务端验证
		String vMsg = "";
		if (SysFun.isNullOrEmpty(content)) {
			vMsg += "联系方式不能为空";
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			defaultView(request, response);
			return;
		}
		//
		Long memberId = getLoginUser(request).getUserId();
		String contentType = "contact";
		Boolean vIsOk = false;
		try {
			vIsOk = bloggerExService.edit(memberId, contentType, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vMsg = "原因为:" + e.getMessage();
		}
		if (vIsOk) {
			out.println("<script>");
			out.println("alert('保存成功');");
			out.println("location.href='BloggerEx_contact';");
			out.println("</script>");
		} else {
			vMsg += "保存失败!" + vMsg;
			request.setAttribute("msg", vMsg);
			defaultView(request, response);
		}

	}

	private void defaultView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// System.out.println(request.getAttribute("UIConst.FK_LOGINUSER_KEY")+" 123");
		Long memberId = getLoginUser(request).getUserId();

		String contentType = "contact";

		// 获得content
		String content = "";
		BloggerEx bloggerEx = bloggerExService.loadBySearch(memberId, contentType);
		if (bloggerEx != null) {
			content = bloggerEx.getContent();
		}

		request.setAttribute("content", content);
		String toPage = "/WEB-INF/member/BloggerEx_contact.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
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
