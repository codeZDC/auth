package com.tuzhi.auth.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuzhi.auth.common.Constants;

/**
 *
 * author : codeZ createdTime: 2018-06-04 10:12:07
 *
 */

public class AuthFilter extends ZFilter {

	private Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) {
		UrlControlResolver resolver = getBean(UrlControlResolver.class);
		return resolver.isAccessUrl(request);
	}

	@Override
	public void onAccessDenied(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendError(403);
			//response.sendRedirect(request.getContextPath()+"/403.html");
		} catch (IOException e) {
			Logger.error("用户访问了没有权限的目录,设置状态码403的时候出现错误!");
			Logger.error(e.getMessage());
		}
	}

	
	@Override
	public void toLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + "/login.html?" +request.getServletPath() );
			//request.getRequestDispatcher("/login.html").forward(request, response);//这样处理就会存在页面相对路径不对头
		} catch (Exception e) {
			Logger.error("转发到登录页面发生错误!");
			Logger.error(e.getMessage());
		} 
	}

	@Override
	public boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response) {
		// 判断用户是否登录
		if (request.getSession().getAttribute(Constants.SESSION_USER) != null)
			return true;
		return false;
	}

}
