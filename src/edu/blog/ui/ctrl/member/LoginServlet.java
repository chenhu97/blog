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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/member/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService memberService = new MemberServiceImpl();
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		javax.servlet.ServletContext application =request.getServletContext();
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletConfig config = getServletConfig();
		java.io.PrintWriter out = response.getWriter();
		//response.setContentType("text/html;charset=utf-8");
		String oper = request.getParameter("oper");
		if(oper==null) {
			oper="";
		}else {
			oper = oper.trim().toLowerCase();
		}
		//根据不同的操作类型，调用不同的处理方法
		switch(oper) {
			case "logindeal":
				loginDeal(request,response);//列表页面
				break;
			default:
				loginView(request,response);
				break;
		}
	}

	protected void loginDeal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		javax.servlet.ServletContext application =request.getServletContext();
		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletConfig config = getServletConfig();
		java.io.PrintWriter out = response.getWriter();
		//1.读取数据
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		//回显准备
		request.setAttribute("userName", userName);
		String toPage = "/WEB-INF/member/login.jsp";
		//2.为空性判断
		if(SysFun.isNullOrEmpty(userName)) {
			request.setAttribute("msg", "账号不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if(SysFun.isNullOrEmpty(userPass)) {
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		//3. 数据获取
		Member bean = memberService.loadByName(userName);
		if(bean == null) {
			request.setAttribute("msg", "账号不存在");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		//4. 业务逻辑之密码判断
		if(!bean.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码不正确");
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		//5a) 登录成功 保存会话
		session.setAttribute(UIConst.FG_LOGINUSER_KEY, bean);
		
		//5b) 
		BloggerService bloggerService = new BloggerServiceImpl();
		Blogger blogger = bloggerService.load(bean.getUserId());
		if(blogger != null) {
			session.setAttribute(UIConst.OTHER_LOGINUSER_KEY, blogger);
		}
		
		response.sendRedirect("Main");
	}

	protected void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toPage = "/WEB-INF/member/login.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
