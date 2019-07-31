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
import edu.uc.ui.ctrl.*;


/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/admin/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		javax.servlet.http.HttpSession session=request.getSession();
		javax.servlet.ServletContext application=request.getServletContext();
		javax.servlet.ServletConfig config=getServletConfig();
		java.io.PrintWriter out=response.getWriter();
		
		String userName = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		//回显
		request.setAttribute("userName", userName);
		//request.setAttribute("usePass", userPass);
		if(SysFun.isNullOrEmpty(userName)) {
			request.setAttribute("msg", "账号不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(SysFun.isNullOrEmpty(userPass)) {
			
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		
		Member bean=memberService.loadByName(userName);
		if(bean==null) {
			request.setAttribute("msg", "账号不存在");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	
		if(!bean.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码不正确");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(!bean.getUserName().equalsIgnoreCase("admin")) {
			request.setAttribute("msg", "不是管理员");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		session.setAttribute(UIConst.BG_LOGINUSER_KEY, bean);
		response.sendRedirect("main.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
