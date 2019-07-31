package edu.blog.ui.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liuvei.common.SysFun;

import edu.blog.bean.*;
import edu.blog.service.MemberService;
import edu.blog.service.impl.MemberServiceImpl;

/**
 * Servlet implementation class MemberUpdateViewServlet
 */
@WebServlet("/member_update.do")
public class MemberUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MemberService memberService = new MemberServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberUpdateViewServlet() {
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

		String id = request.getParameter("id");
		// 2) 服务端验证:主键的为空性判断
		if (SysFun.isNullOrEmpty(id)) {
			out.print("<script>alert('id不能为空。');location.href='member_list.do';</script>");
			return;
		}
		// 3) 服务端验证:主键的合法性判断
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			out.print("<script>alert('id不是数字。');location.href='member_list.do';</script>");
			return;
		}
		// 4) 业务功能: 从数据库获取数据
		Member bean = memberService.load(vId);

		if (bean == null) {
			out.print("<script>alert('不存在对应的数据。');location.href='member_list.do';</script>");
			return;
		}

		StringBuffer sbHtml = new StringBuffer();

		sbHtml.append(getHtmlHeader(bean));
		sbHtml.append(getHtmlContent(bean));
		sbHtml.append(getHtmlFooter());

		out.println(sbHtml.toString());

	}

	public StringBuffer getHtmlHeader(Member bean) {
		StringBuffer sbHeader = new StringBuffer();
		sbHeader.append("<!DOCTYPE html>\r\n");
		sbHeader.append("<html lang=\"en\">\r\n");
		sbHeader.append("\t<head>\r\n");
		sbHeader.append("\t<meta charset=\"UTF-8\">\r\n");
		sbHeader.append("\t<title>会员【" + bean.getUserId() + "】信息修改</title>\r\n");
		//sbHeader.append("\t<link href='static/member/css/member_list.css' rel=\"stylesheet\" />\r\n");
		sbHeader.append("\t<script src='static/member/js/jquery-1.12.4.js'></script>\r\n");
		sbHeader.append("\t<script src='static/member/js/member_list.js'></script>\r\n");
		sbHeader.append("\t</head>\r\n");
		sbHeader.append("<body>\r\n");

		return sbHeader;
	}

	public Object getHtmlContent(Member bean) {
		// TODO Auto-generated method stub
		StringBuffer sbCtx = new StringBuffer();

		sbCtx.append("<div class='ctx'>\r\n");
		sbCtx.append("<form action='member_update_deal.do' method='get'>\r\n");

		sbCtx.append("<input name='userId' type='hidden' value='" + bean.getUserId() + "'>");

		sbCtx.append("<div>\r\n");
		sbCtx.append("账号:<input name='userName' type='text' value='" + bean.getUserName() + "'>");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("密码:<input name='userPass' type='password' value=''/>");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("确认密码:<input name='userPass2' type='password' value=''/>");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("昵称:<input name='nickName' type='text' value='" + bean.getNickName() + "'>");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("邮箱:<input name='email' type='text' value='" + bean.getEmail() + "'>");
		sbCtx.append("</div>\r\n");

		sbCtx.append("<div>\r\n");
		sbCtx.append("<input type='submit' value='提交修改' />");
		sbCtx.append("<input type='reset' value='重置' />");
		sbCtx.append("</div>\r\n");

		sbCtx.append("</form>");

		sbCtx.append("<div>\r\n");
		sbCtx.append("<button onclick=\"javascript:location.href='member_list.do'\">返回上级</button>\r\n");
		sbCtx.append("</div>\r\n");
		sbCtx.append("</div>");

		return sbCtx;
	}

	public Object getHtmlFooter() {
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
