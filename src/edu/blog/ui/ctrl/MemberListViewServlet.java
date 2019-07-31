package edu.blog.ui.ctrl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.blog.bean.*;
import edu.blog.service.*;
import edu.blog.service.impl.*;

/**
 * Servlet implementation class MemberListViewServlet
 */
@WebServlet("/member_list.do")
public class MemberListViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public MemberService memberService = new MemberServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberListViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
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

	private StringBuffer getHtmlHeader() {
		StringBuffer sbHeader = new StringBuffer();
		sbHeader.append("<!DOCTYPE html>\r\n");
		sbHeader.append("<html lang=\"en\">\r\n");
		sbHeader.append("\t<head>\r\n");
		sbHeader.append("\t<meta charset=\"UTF-8\">\r\n");
		sbHeader.append("\t<title>会员列表</title>\r\n");
		//sbHeader.append("\t<link href='static/member/css/member_list.css' rel=\"stylesheet\" />\r\n");
		sbHeader.append("\t<script src='static/member/js/jquery-1.12.4.js'></script>\r\n");
		sbHeader.append("\t<script src='static/member/js/member_list.js'></script>\r\n");
		sbHeader.append("</head>\r\n");
		sbHeader.append("<body>\r\n");

		return sbHeader;
	}

	private StringBuffer getHtmlContent() {
		StringBuffer sbCtx = new StringBuffer();
		sbCtx.append("<div class='ctx'>\r\n");
		sbCtx.append("<div>\r\n");
		sbCtx.append("<button onclick=\"javascript:location.href='member_insert.do'\">添加</button>\r\n");
		sbCtx.append("<button onclick='javascript:datadel()'>批量删除</button>\r\n");
		sbCtx.append("</div>\r\n");
		sbCtx.append("<table border='1' cellspacing='0' cellpadding='8'>\r\n");
		sbCtx.append("<tr>\r\n");
		sbCtx.append("<td>\r\n");
		sbCtx.append("<input type='checkbox' id='checkall' value='' />\r\n");
		sbCtx.append("</td>\r\n");
		sbCtx.append("<td>\r\n");
		sbCtx.append("序号");
		sbCtx.append("</td>\r\n");

		sbCtx.append("<td>\r\n");
		sbCtx.append("id");
		sbCtx.append("</td>\r\n");

		sbCtx.append("<td>\r\n");
		sbCtx.append("账号");
		sbCtx.append("</td>\r\n");

		sbCtx.append("<td>\r\n");
		sbCtx.append("昵称");
		sbCtx.append("</td>\r\n");

		sbCtx.append("<td>\r\n");
		sbCtx.append("Email");
		sbCtx.append("</td>\r\n");

		sbCtx.append("<td>\r\n");
		sbCtx.append("操作");
		sbCtx.append("</td>\r\n");

		int n = 1;
		List<Member> vlist = memberService.list();
		for (Member bean : vlist) {
			sbCtx.append("<tr>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append("<input type='checkbox' value='" + bean.getUserId() + "'/>\r\n");
			sbCtx.append("</td>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append(n);
			sbCtx.append("</td>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append(bean.getUserId());
			sbCtx.append("</td>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append(bean.getUserName());
			sbCtx.append("</td>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append(bean.getNickName());
			sbCtx.append("</td>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append(bean.getEmail());
			sbCtx.append("</td>\r\n");

			sbCtx.append("<td>\r\n");
			sbCtx.append("<a href = 'member_delete_deal.do?id=" + bean.getUserId() + "'>删除</a>\r\n");
			sbCtx.append("&nbsp;&nbsp;&nbsp;");

			sbCtx.append("<a href = 'member_update.do?id=" + bean.getUserId() + "'>修改</a>\r\n");
			sbCtx.append("&nbsp;&nbsp;&nbsp;");

			sbCtx.append("<a href = 'member_detail.do?id=" + bean.getUserId() + "'>查看</a>\r\n");
			sbCtx.append("&nbsp;&nbsp;&nbsp;");

			sbCtx.append("</td>\r\n");
			sbCtx.append("</tr>\r\n");

			n++;
		}
		sbCtx.append("</table>\r\n");
		sbCtx.append("</div>\r\n");

		return sbCtx;
	}

	private StringBuffer getHtmlFooter() {
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
