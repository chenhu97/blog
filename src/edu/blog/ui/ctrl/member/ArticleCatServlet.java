package edu.blog.ui.ctrl.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;

import edu.blog.bean.*;
import edu.blog.service.*;
import edu.blog.service.impl.*;
import edu.uc.ui.ctrl.UIConst;

/**
 * Servlet implementation class ArticleCatServlet
 */
@WebServlet("/member/ArticleCat")
public class ArticleCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleCatService articleCatService = new ArticleCatServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ArticleCatServlet() {
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

		javax.servlet.http.HttpSession session = request.getSession();
		javax.servlet.ServletContext application = getServletContext();
		javax.servlet.ServletConfig config = getServletConfig();
		java.io.PrintWriter out = response.getWriter();
		String oper = request.getParameter("oper");
		if (oper == null) {
			oper = "";
		} else {
			oper = oper.trim().toLowerCase();
		}
		// 根据不同的操作类型,调用不同的处理方法
		switch (oper) {
		case "list":
			listView(request, response);
			break;
		case "listdeal":
			listDeal(request, response);
			break;
		case "insert":
			insertView(request, response);
			break;
		case "insertdeal":
			insertDeal(request, response);
			break;
		case "update":
			updateView(request, response);
			break;
		case "updatedeal":
			updateDeal(request, response);
			break;
		case "deletedeal":
			deleteDeal(request, response);
			break;
		case "detail":
			detailView(request, response);
			break;
		default:
			System.out.println("操作不存在");
			break;
		}
	}

	private void listView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		javax.servlet.http.HttpSession session = request.getSession();

		Member bean = (Member) session.getAttribute(UIConst.FG_LOGINUSER_KEY);
		Long memberId = bean.getUserId();
		String name = null;

		List<ArticleCat> vDataList = null;
		//  -----------------------------------------------------------------------
		//  分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex    
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
		// pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		// 分页步骤2.1. 定义记录数变量    
		Long rowCount = 0L;

		// 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实 际换成其它方法
		rowCount = articleCatService.countByMemberIdAndName(memberId, name);
		// 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算    
		pagerItem.changeRowCount(rowCount);
		// 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它 方法    
		vDataList = articleCatService.pagerByMemberIdAndName(memberId, name, pagerItem.getPageNum(),
				pagerItem.getPageSize());

		//  分页步骤2.5. 设置页面的跳转url    
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		//  分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问  
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);
		// -----------------------------------------------------------------------
		// 转发到页面    
		String toPage = "/WEB-INF/member/ArticleCat_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	private void listDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		javax.servlet.http.HttpSession session = request.getSession();
		Member bean = (Member) session.getAttribute(UIConst.FG_LOGINUSER_KEY);
		Long memberId = bean.getUserId();
		//String name = "当你看到这个";
		String name = request.getParameter("catName");//一开始是空的
		request.setAttribute("catName", name);
		//request.setAttribute("catName", name);
		List<ArticleCat> vDataList = null;
		//  -----------------------------------------------------------------------
		//  分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex    
		PagerItem pagerItem = new PagerItem();
		pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
		// pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
		// 分页步骤2.1. 定义记录数变量    
		Long rowCount = 0L;

		// 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实 际换成其它方法
		rowCount = articleCatService.countByMemberIdAndName(memberId, name);
		// 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算    
		pagerItem.changeRowCount(rowCount);
		// 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它 方法    
		vDataList = articleCatService.pagerByMemberIdAndName(memberId, name, pagerItem.getPageNum(),
				pagerItem.getPageSize());
		//  分页步骤2.5. 设置页面的跳转url    
		pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
		//  分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问  
		request.setAttribute("pagerItem", pagerItem);
		request.setAttribute("DataList", vDataList);
		// 转发页面
		String toPage = "/WEB-INF/member/ArticleCat_list.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);

	}

	private void insertView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String toPage = "/WEB-INF/member/ArticleCat_insert.jsp";
		request.getRequestDispatcher(toPage).forward(request, response);
	}

	private void insertDeal(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		java.io.PrintWriter out = response.getWriter();
		javax.servlet.http.HttpSession session = request.getSession();

		Member loginUser = (Member) session.getAttribute(UIConst.FG_LOGINUSER_KEY);
		Long memberId = loginUser.getUserId();

		String catName = request.getParameter("catName");
		// 为了在输入页面回显原来的旧值，需要将原来的旧值放到作用域中,在页面中进行获取
		request.setAttribute("catName", catName);
		// 服务端验证
		String vMsg = "";
		if (SysFun.isNullOrEmpty(catName)) {
			vMsg += "类目名称不能为空";
		}
		// 如果验证失败 ,则将失败的内容放到作用域变量并转发到页面
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			insertView(request, response);
			return;
		}
		//执行业务
		ArticleCat bean = new ArticleCat();
		bean.setCatName(catName);
		bean.setMemberId(memberId);
		
		Long result = 0L;

		try {
			result = articleCatService.insert(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vMsg = " 添加失败" + e.getMessage();
		}
		if (result > 0) {
			out.println("<script>");
			out.println("alert('添加成功')");
			out.println("location.href='ArticleCat?oper=list'");
			out.println("</script>;");
		} else {
			request.setAttribute("msg", vMsg);
			insertView(request, response);
		}
	}

	private void updateView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		java.io.PrintWriter out = response.getWriter();
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			ArticleCat bean = articleCatService.load(iId);
			if (bean != null) {
				// 转发向的界面可以读到
				request.setAttribute("catId", bean.getCatId());
				request.setAttribute("catName", bean.getCatName());

				String toPage = "/WEB-INF/member/ArticleCat_update.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
				return;
			}
			out.println("<script>");
			out.println("alert('数据不存在!');");
			out.println("location.href='ArticleCat?oper=list';");
			out.println("</script>");
		}

	}

	private void updateDeal(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		javax.servlet.http.HttpSession session = request.getSession();

		Member loginUser = (Member) session.getAttribute(UIConst.FG_LOGINUSER_KEY);
		Long memberId = loginUser.getUserId();

		String vId = request.getParameter("catId");
		String catName = request.getParameter("catName");
		request.setAttribute("catId", vId);
		request.setAttribute("catName", catName);
		String vMsg = "";
		if (SysFun.isNullOrEmpty(vId)) {
			vMsg += "类目Id不能为空";

		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
			return;
		}

		if (SysFun.isNullOrEmpty(catName)) {
			vMsg += "类目名称不能为空";
		}
		// 如果验证失败 ,则将失败的内容放到作用域变量并转发到页面
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
			return;
		}
		// 数据库验证
		Long iId = 0L;
		ArticleCat bean = null;
		if (SysFun.isNullOrEmpty(vId)) {
			vMsg += "没有指定主键";
		} else {
			iId = SysFun.parseLong(vId);
			bean = articleCatService.load(iId);
			if (bean == null) {
				vMsg += "数据不存在";
			}
		}
		if (!SysFun.isNullOrEmpty(vMsg)) {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
			return;
		}
		// 执行业务
		Long result = 0L;
		bean.setCatName(catName);
		bean.setMemberId(memberId);
		try {
			result = articleCatService.update(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			vMsg += "修改" + e.getMessage();
		}
		if (result > 0) {
			out.println("<script>");
			out.println("alert('修改成功');");
			out.println("location.href='ArticleCat?oper=list'");
			out.println("</script>");
		} else {
			request.setAttribute("msg", vMsg);
			updateView(request, response);
		}

	}

	private void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			Long result = articleCatService.delete(iId);
			if (result > 0) {
				out.print("ok");// 不能使用println
				return;
			}
		}
		out.print("nook");

	}

	private void detailView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		java.io.PrintWriter out = response.getWriter();
		String vId = request.getParameter("id");
		if (!SysFun.isNullOrEmpty(vId)) {
			Long iId = SysFun.parseLong(vId);
			ArticleCat bean = articleCatService.load(iId);
			if (bean != null) {
				request.setAttribute("bean", bean);
				String toPage = "/WEB-INF/member/ArticleCat_detail.jsp";
				request.getRequestDispatcher(toPage).forward(request, response);
			}
		}
		out.println("<script>");		
		out.println("alert('数据不存在');");
		out.println("location.href='ArticleCat?oper=list'");
		out.println("</script>");
		

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
