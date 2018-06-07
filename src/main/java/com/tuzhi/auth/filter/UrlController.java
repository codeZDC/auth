package com.tuzhi.auth.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuzhi.auth.common.Constants;
import com.tuzhi.auth.mapper.UrlMapper;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-07 10:08:09
 *
 */

@Service
public class UrlController implements UrlControlResolver{

	@Autowired
	private UrlMapper urlMapper;
	
	@Override
	public boolean isAccessUrl(HttpServletRequest request) {
		String path = request.getServletPath();
		String username = (String)request.getSession().getAttribute(Constants.SESSION_USER);
		//获取用户的权限列表,判断当前访问路径是否有权限
		List<String> permissions = urlMapper.getPermissions(username);
		for (String string : permissions) {
			if(path.equals(string))
				return true;
		}
		System.err.println("用户没有权限访问该地址 : " + path);
		return false;
	}

}
