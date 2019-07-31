package edu.blog.ui.ctrl.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.blog.bean.Blogger;
import edu.blog.bean.Member;
import edu.blog.service.BloggerService;
import edu.blog.service.MemberService;
import edu.blog.service.impl.BloggerServiceImpl;
import edu.blog.service.impl.MemberServiceImpl;
import edu.uc.ui.ctrl.UIConst;

/**
 * Servlet implementation class BloggerServlet
 */
@WebServlet("/member/Blogger")
public class BloggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberServiceImpl();
	BloggerService bloggerService = new BloggerServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BloggerServlet() {
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
		case "applydeal":
			applyDeal(request, response);
			break;
		default:
			applyView(request, response);
			break;
		}

	}

	private void applyDeal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub	
		java.io.PrintWriter io = response.getWriter();
		javax.servlet.ServletConfig config = getServletConfig();
		javax.servlet.ServletContext application = request.getServletContext();
		javax.servlet.http.HttpSession session = request.getSession();
		
		// 1) 获取前端的请求的数据
		String nickName = request.getParameter("nickName");
		String blogTitle = request.getParameter("blogTitle");
		String blogTitleEx = request.getParameter("blogTitleEx");
		String blogFolder = request.getParameter("blogFolder");
		
		//回显
		request.setAttribute("nickName", nickName);
		request.setAttribute("blogTitle", blogTitle);
		request.setAttribute("blogTitleEx", blogTitleEx);
		request.setAttribute("blogFolder", blogFolder);

		//1) 为空性判断
		String toPage = "/WEB-INF/member/Blogger_apply.jsp";
			if(SysFun.isNullOrEmpty(nickName)) {
				request.setAttribute("msg", "博主昵称不能为空！");
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			if(SysFun.isNullOrEmpty(blogTitle)) {
				request.setAttribute("msg", "博客主标题不能为空！");
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			if(SysFun.isNullOrEmpty(blogTitleEx)) {
				request.setAttribute("msg", "博客副标题不能为空！");
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			if(SysFun.isNullOrEmpty(blogFolder)) {
				request.setAttribute("msg", "博客路径不能为空！");
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			//2) 服务端数据合法性判断:博主昵称或博客路径是否已存在
			
			Long num = 0L;
			num = bloggerService.countByName(nickName);
			if(num>0) {
				request.setAttribute("msg", "当前博主昵称已被使用");
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			Blogger item = bloggerService.loadByFolder(blogFolder);
			if(item != null) {
				request.setAttribute("msg", "当前博客路径已被使用");
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			
			//3) 执行业务: 添加博主信息
			//获得当前登录用户
			Member loginUser =(Member) session.getAttribute(UIConst.FG_LOGINUSER_KEY);
			
			Blogger bean = new Blogger();
			
			bean.setNickName(nickName);
			bean.setBlogTitle(blogTitle);
			bean.setBlogTitleEx(blogTitleEx);
			bean.setBlogFolder(blogFolder);
			bean.setMemberId(loginUser.getUserId());
			bean.setIsEnable(0L);//0为未审核状态
			
			Long result = 0L;
			try {
				result = bloggerService.insert(bean);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				request.setAttribute("msg", "博主申请失败,原因如下:" + e.getMessage());
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			if(result > 0) {
				session.setAttribute(UIConst.OTHER_LOGINUSER_KEY, bean);
				response.sendRedirect("Blogger");
			}else {
				request.setAttribute("msg", "申请失败");
				applyView(request, response);
			}
	}

	private void applyView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(request.getAttribute("UIConst.FK_LOGINUSER_KEY")+" 123");
		String toPage = "/WEB-INF/member/Blogger_apply.jsp";
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
