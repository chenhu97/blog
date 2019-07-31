package edu.blog.bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieFun {
	/**
	 * Cookie要先写才能读
	 * 
	 * @param request
	 *            请求对象
	 * @param key
	 * @return
	 */
	public static Cookie readCookie(HttpServletRequest request, String key) {
		Cookie tmpCookies = null;
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0) {
			return tmpCookies;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(key)) {
				tmpCookies = cookie;
				break;
			}
		}
		return tmpCookies;
	}

	/**
	 * Cookie要先写才能读
	 * 
	 * @param response
	 *            响应
	 * @param cookie
	 * 
	 */
	public static void writeCookie(HttpServletResponse response, Cookie cookie) {

		response.addCookie(cookie);

	}

	/**
	 * Cookie要先写才能读
	 * 
	 * @param request
	 *            请求对象
	 * @param key
	 *            cookie的键
	 * @return cookie的值
	 */
	public static String read(HttpServletRequest request, String key) {

		String value = "";
		Cookie cookie = readCookie(request, key);
		if (cookie != null) {
			value = cookie.getValue();
		}
		return value;
	}

	/**
	 * 
	 * 
	 * @param key
	 *            cookie的键
	 * @param value
	 *            cookie的值
	 * @param expiry
	 *            存活期:单位为秒
	 * @param path
	 *            cookie生效路径
	 * @return
	 */
	public static Cookie getNewCookie(String key, String value, int expiry, String path) {
		Cookie cookie = null;
		cookie = new Cookie(key, value);
		// cookie存活期,单位是秒,30*24*60*60表示存活期一个月
		cookie.setMaxAge(expiry);
		cookie.setPath(path);// 实例："/"表示根目录
		return cookie;

	}

	/**
	 * 
	 * @param response
	 *            响应对象
	 * @param key
	 *            cookie的键
	 * @param value
	 *            cookie的值
	 * @param expiry
	 *            存活期:单位为秒
	 * @param path
	 *            cookie生效路径
	 */
	public static void write(HttpServletResponse response, String key, String value, int expiry, String path) {
		Cookie cookie = getNewCookie(key, value, expiry, path);
		response.addCookie(cookie);
	}

	/**
	 * 
	 * @param response
	 *            响应对象
	 * @param key
	 *            cookie的键
	 * @param value
	 *            cookie的值
	 */
	public static void write(HttpServletResponse response, String key, String value) {
		write(response, key, value, 7 * 24 * 60 * 60, "/");
	}

}
