package com.tuzhi.auth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.Constants;
import com.tuzhi.auth.common.Res;
import com.tuzhi.auth.exception.BusinessException;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-04 13:53:54
 *
 */


@Controller
public class LoginCtrl extends BaseCtrl{

	@RequestMapping
	public void index(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.sendRedirect(request.getContextPath() + "/index.html?"+System.currentTimeMillis());
	}
	
	@PostMapping("login")
	@ResponseBody
	public Res login(String username , String password){
		
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password))
			throw new BusinessException("用户名或密码不能为空!");
		if(!username.equals(password))
			throw new BusinessException("用户名或密码不正确!");
		session.setAttribute(Constants.SESSION_USER,username);
		return Res.success("登录成功!");
	}
	
	@RequestMapping("logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().removeAttribute(Constants.SESSION_USER);
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/index.html?"+System.currentTimeMillis());
	}
	
	@RequestMapping("getUserMsg")
	@ResponseBody
	public String getUserMsg(){
		String username = (String)session.getAttribute(Constants.SESSION_USER);
		return username;
	}
	
	
}
