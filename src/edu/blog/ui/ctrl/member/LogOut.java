package edu.blog.ui.ctrl.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uc.ui.ctrl.UIConst;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/member/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
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
		//移除会话数据
		session.removeAttribute(UIConst.FG_LOGINUSER_KEY);
		session.removeAttribute(UIConst.OTHER_LOGINUSER_KEY);
		//设置当前会话失效
		session.invalidate();
		//跳转到登录界面
		response.sendRedirect("Login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
