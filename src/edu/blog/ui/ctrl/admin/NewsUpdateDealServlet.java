package edu.blog.ui.ctrl.admin;

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
import edu.util.UploadFileResult;
import edu.util.UploadFileUtil;

/**
 * Servlet implementation class MemberUpdateDealServlet
 */
@WebServlet("/admin/news_update.do")
public class NewsUpdateDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public NewsService newsService = new NewsServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsUpdateDealServlet() {
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
		// request.setCharacterEncoding("utf-8");
		// response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");

		String toLoginURL = checkLogin(request, response);
		if (toLoginURL != null) {
			response.sendRedirect(toLoginURL);
			return;
		}
		java.io.PrintWriter out = response.getWriter();
		// 0)
		UploadFileResult uploadFileResult = UploadFileUtil.uploadFile(request);

		// 1) 获得请求数据
		String id = (String) request.getAttribute("id");

		String catId = (String) request.getAttribute("catId");

		String title = (String) request.getAttribute("title");

		String author = (String) request.getAttribute("author");

		String summary = (String) request.getAttribute("summary");

		String picPath = (String) request.getAttribute("picPath");

		String content = (String) request.getAttribute("content");

		String status = (String) request.getAttribute("status");

		String remark = (String) request.getParameter("remark");

		// 放到request作用域，以便回显,标准做法
		request.setAttribute("id", id);
		request.setAttribute("catId", catId);
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("summary", summary);
		request.setAttribute("content", content);
		request.setAttribute("status", status);
		request.setAttribute("remark", remark);

		// 2) 服务端验证，为空性判断
		String msg = "";
		String toPage = "/admin/news_update.jsp";

		if (SysFun.isNullOrEmpty(id)) {
			msg = "id不能为空";

		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(title)) {

			msg = "新闻标题不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(picPath)) {
			msg = "路径不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(author)) {
			msg = "作者信息不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(summary)) {
			summary = content.substring(0, 100);

		}

		// 3) 服务端验证：数据合法性判断
		Long vId = 0L;
		try {
			vId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			msg = "id不是数字";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		// 4) 业务操作

		News bean = newsService.load(vId);

		if (bean == null) {
			msg = "数据不存在!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		bean.setCatId(SysFun.parseLong(catId,0L));
		bean.setTitle(title);
		bean.setAuthor(author);
		bean.setSummary(summary);
		bean.setStatus(status);
		bean.setRemark(remark);
		bean.setUpdateOn(new java.util.Date());
		//上传图片处理
		if(uploadFileResult.getCode()==0L) {
			bean.setPicPath(uploadFileResult.getDesc());
		}
		// 5) 业务操作:执行真正的修改
		Long result = 0L;
		try {
			result = newsService.update(bean, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg = e.getMessage();
		}
		// 6) 结果响应
		if (result > 0) {
			out.print("<script>alert('修改成功');location.href='news_list.jsp';</script>");
		} else {
			msg = "修改失败" + msg;
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
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
