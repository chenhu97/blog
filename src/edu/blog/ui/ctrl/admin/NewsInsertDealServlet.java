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
 * Servlet implementation class NewsCatInsertDealServlet
 */
@WebServlet("/admin/news_insert.do")
public class NewsInsertDealServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public NewsService newsService = new NewsServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsInsertDealServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();

		String toLoginURL = checkLogin(request, response);
		if (toLoginURL != null) {
			response.sendRedirect(toLoginURL);
			return;
		}
		// 0)由上传的工具类处理multipart方式的请求数据
		UploadFileResult uploadFileResult = UploadFileUtil.uploadFile(request);

		// 1)获取数据 pdf中是使用
		// String catId = (String)request.getAttribute("catId");
		// 区别在于?
		String catId = (String) request.getAttribute("catId");
		String title = (String) request.getAttribute("title");
		String author = (String) request.getAttribute("author");
		String summary = (String) request.getAttribute("summary");
		String picPath = (String) request.getAttribute("picPath");
		String content = (String) request.getAttribute("content");
		String status = (String) request.getAttribute("status");
		String remark = (String) request.getAttribute("remark");

		request.setAttribute("catId", catId);
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("summary", summary);
		request.setAttribute("picPath", picPath);
		request.setAttribute("content", content);
		request.setAttribute("status", status);
		request.setAttribute("remark", remark);

		// 2) 服务端验证: 为空性验证
		String msg = "";

		String toPage = "/admin/news_insert.jsp";
		if (SysFun.isNullOrEmpty(catId)) {
			msg = "新闻类目不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(title)) {
			msg = "标题不能为空!";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(author)) {
			msg = "作者不能为空!";
		}

		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (!SysFun.isNullOrEmpty(picPath)) {
			msg = "新闻路径不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(content)) {
			msg = "新闻内容不能为空";
		}
		if (!SysFun.isNullOrEmpty(msg)) {
			request.setAttribute("msg", msg);
			request.getRequestDispatcher(toPage).forward(request, response);
			return;
		}
		if (SysFun.isNullOrEmpty(summary)) {
			summary = content.substring(0, 100);
		}

		// 3) 服务器验证:合法性判断

		// 真正执行添加操作
		News bean = new News();

		bean.setCatId(SysFun.parseLong(catId,1L));
		bean.setTitle(title);
		bean.setAuthor(author);
		bean.setSummary(summary);

		bean.setStatus(status);
		bean.setRemark(remark);

		bean.setCreateOn(new java.util.Date());
		bean.setUpdateOn(new java.util.Date());

		if (uploadFileResult.getCode() == 0L) {
			bean.setPicPath(uploadFileResult.getDesc());
		}

		Long result = 0L;
		String msg1 = " ";
		try {
			result = newsService.insert(bean, content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			msg1 = e.getMessage();
		}

		// 结果响应
		if (result > 0) {
			out.print("<script>alert('添加成功!');location.href='news_list.jsp';</script>");
			return;
		} else {
			request.setAttribute("msg1", msg1);
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
