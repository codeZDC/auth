package com.tuzhi.auth.filter;

import javax.servlet.http.HttpServletRequest;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-07 10:02:26
 *
 */

public interface UrlControlResolver {

	/**
	 * 判断当前用户是否有权限访问该路径
	 * @param request中包含用户访问路径信息
	 * @return 是否有权限
	 */
	boolean isAccessUrl(HttpServletRequest request);
	
	
}
