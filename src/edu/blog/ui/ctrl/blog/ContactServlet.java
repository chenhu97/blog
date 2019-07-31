package edu.blog.ui.ctrl.blog;

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
 * Servlet implementation class DetailServlet
 */
@WebServlet("/blog/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleService articleService = new ArticleServiceImpl();
	BloggerExService bloggerExService = new BloggerExServiceImpl();
	MemberService memberService = new MemberServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContactServlet() {
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
		response.setContentType("text/html;charset=utf-8");

		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}
		switch (oper) {
		default:
			defaultView(request, response);
			break;
		}
	}

	private void defaultView(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		javax.servlet.ServletContext application = request.getServletContext();
		// 1)获得当前查看的博主对象
		Blogger blogger = (Blogger) request.getAttribute("ViewBlogger");
		// 2)获得参数
		Long memberId = blogger.getMemberId();
		String contentType = "contact";

		// 3)取得对应类型的对象
		BloggerEx bloggerEx = bloggerExService.loadBySearch(memberId, contentType);

		request.setAttribute("bean", bloggerEx);
		
		// 转发到页面
		String toPage = "/WEB-INF/blog/contact.jsp";
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
