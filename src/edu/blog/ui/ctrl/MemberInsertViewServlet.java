package edu.blog.ui.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.blog.service.MemberService;
import edu.blog.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class MemberInsertViewServlet
 */
@WebServlet("/member_insert.do")
public class MemberInsertViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberInsertViewServlet() {
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

		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append(getHtmlHeader());
		sbHtml.append(getHtmlContent());
		sbHtml.append(getHtmlFooter());
		out.println(sbHtml.toString());
	}

	public StringBuffer getHtmlHeader() {
		// TODO Auto-generated method stub
		StringBuffer sbHeader = new StringBuffer();
		sbHeader.append("<!DOCTYPE html>\r\n");
		sbHeader.append("<html lang=\"en\">\r\n");
		sbHeader.append("\t<head>\r\n");
		sbHeader.append("\t<meta charset=\"UTF-8\">\r\n");
		sbHeader.append("<title>添加会员</title>");
		sbHeader.append("</head>\r\n");
		sbHeader.append("<body>\r\n");

		return sbHeader;
	}

	public StringBuffer getHtmlContent() {
		// TODO Auto-generated method stub
		StringBuffer sbCtx = new StringBuffer();
		sbCtx.append("<div class = 'ctx'>\r\n");
		sbCtx.append("<form method='get' action = 'member_insert_deal.do'>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("账号:<input type='text' name='userName' value= '' />");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("密码:<input type='password' name='userPass' value='' />");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("确认密码:<input type='password' name='userPass2' value='' />");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("昵称:<input type='text' name='nickName' value='' />");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("邮箱:<input type='email' name='email' value='' />");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("<input type='submit' value='确认添加' />&nbsp;&nbsp;&nbsp;&nbsp;");
		sbCtx.append("<input type='reset' value='重置' />");

		sbCtx.append("</div>\r\n");

		sbCtx.append("</form>");

		sbCtx.append("<div>\r\n");
		sbCtx.append("<button onclick=\"javascript:location.href='member_list.do'\">返回上级</button>\r\n");
		sbCtx.append("</div>\r\n");

		sbCtx.append("</div>\r\n");
		return sbCtx;
	}

	public StringBuffer getHtmlFooter() {
		// TODO Auto-generated method stub
		StringBuffer sbFooter = new StringBuffer();

		sbFooter.append("</body>\r\n");
		sbFooter.append("</html>\r\n");

		return sbFooter;
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
