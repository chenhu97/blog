package edu.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/*
 * web.xml配置方式
 */
/**
 * 
 * @className:CharacterEncodingFilter
 * @Description:此过滤器用来解决全站中文乱码问题
 * 
 * 
 */
public class CharacterEncodingFilter implements Filter {
	
	
	private FilterConfig filterConfig = null;
	//设置默认的字符编码
	private String defaultCharset  = "UTF-8";
	//Tomcat7及之前需要配置
	private String defaultIsUserWrapper = "false";
	
    public CharacterEncodingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		this.filterConfig = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse rsp, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest request =(HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) rsp;
		
		String charset = filterConfig.getInitParameter("charset");
		if(charset == null) {
			charset = defaultCharset;
		}
		
		//设置请求对象的编码方式
		request.setCharacterEncoding(charset);
		//设置响应请求对象的编码方式
		response.setCharacterEncoding(charset);
		//设置响应的内容类型是text/html,有需要再进行设置
		//response.setContentType("text/html;charset=" + charset);
		
		String isUserWrapper = filterConfig.getInitParameter("isUserWrapper");
		if(isUserWrapper == null) {
			isUserWrapper = defaultIsUserWrapper;
		}
		ServletRequest resultReq = req;
		if(isUserWrapper != null && isUserWrapper.equalsIgnoreCase("true")) {
			resultReq = new MyCharacterEncodingRequest(request);	
		}
		chain.doFilter(resultReq, response);
	}	

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		//得到过滤器的初始化配置信息
		this.filterConfig = filterConfig;
	}
	/*
	 *1.实现与被增强对象相同的接口
	 *2.定义一个变量记住被增强对象
	 *3.定义一个构造求，接收被增强对象
	 *4.覆盖想要增强的方法
	 *5.对于不想增强的方法,直接调用被增强对象(目标对象)的方法
	 */
	class MyCharacterEncodingRequest extends HttpServletRequestWrapper {
		private HttpServletRequest request;
		public MyCharacterEncodingRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			// TODO Auto-generated constructor stub
		}
		//重写getParameter方法
		public String getParamter(String name) {
			
			try {
				//获取参数的值
				String value = this.request.getParameter(name);
				if(value==null) {
					return null;
				}
				//如果不是以get方式提交数据的，就直接返回获取到的值
				if(this.request.getMethod().equalsIgnoreCase("get")) {
					return value;
				}else {
					//如果是以get方式提交数据的，就对数据的值进行转码处理
					value = new String(value.getBytes("ISO8859-1"),this.request.getCharacterEncoding());
					return value;
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}
}
