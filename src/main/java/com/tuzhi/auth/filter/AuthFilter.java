package com.tuzhi.auth.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tuzhi.auth.common.Constants;

/**
 *
 * author : codeZ createdTime: 2018-06-04 10:12:07
 *
 */

public class AuthFilter extends ZFilter {

	@Override
	public boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) {
		UrlControlResolver resolver = getBean(UrlControlResolver.class);
		return resolver.isAccessUrl(request);
	}

	@Override
	public void onAccessDenied(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendError(503);
		} catch (IOException e) {
			System.err.println("权限设置503错误!");
		}
	}

	
	@Override
	public void toLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + "/login.html?" +request.getServletPath() );
			//request.getRequestDispatcher("/login.html").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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
