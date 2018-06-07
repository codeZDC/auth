package com.tuzhi.auth.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * author : codeZ 
 * createdTime: 2018-06-04 09:47:38
 *
 */

public abstract class ZFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger("权限 _ ");

	FilterConfig filterConfig = null;

	private static ApplicationContext context = null;

	String[] excludes = null;
	boolean urlControl = false;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		logger.debug("authentication is init...");

		this.filterConfig = filterConfig;

		// 获取不需要验证的路径
		this.excludes = filterConfig.getInitParameter("excludes").split(",");
		this.urlControl = Boolean.parseBoolean(filterConfig.getInitParameter("urlControl"));
		
		ServletContext servletContext = filterConfig.getServletContext();

		context = WebApplicationContextUtils.getWebApplicationContext(servletContext);

	}

	public <T> T getBean(Class<T> clazz) {
		return context.getBean(clazz);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// 不需要验证的资源直接不拦截
		if (isExcludes(req)){
			chain.doFilter(request, response);
			return;
		}
		//查看是否登录
		if (!isAuthenticated(req, res))
			toLogin(req, res);
		else if (!urlControl || isAccessAllowed(req, res))
			chain.doFilter(request, response);
		else
			onAccessDenied(req, res);
	}
	
	//是否已经登录
	public abstract boolean isAuthenticated(HttpServletRequest request, HttpServletResponse response);
	// 用户需要登录
	public abstract void toLogin(HttpServletRequest request, HttpServletResponse response);
	
	// 用户是否有权限 
	public abstract boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response);
	// 用户没有权限
	public abstract void onAccessDenied(HttpServletRequest request, HttpServletResponse response);


	@Override
	public void destroy() {
		logger.debug("authentication is destroy...");
	}

	private boolean isExcludes(HttpServletRequest request) {

		String path = request.getServletPath();
		
		if (excludes != null) {
			for (String exclude : excludes) {
				if (exclude.contains("^")) {
					if (path.startsWith(exclude.substring(1)))
						return true;
				} else if (exclude.contains("$")) {
					if (path.endsWith(exclude.replace("$", "")))
						return true;
				} else {
					if (path.contains(exclude))
						return true;
				}
			}
		}
		return false;
	}
}
