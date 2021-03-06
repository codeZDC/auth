package com.tuzhi.auth.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.Constants;
import com.tuzhi.auth.common.Res;
import com.tuzhi.auth.domain.LogLogin;
import com.tuzhi.auth.domain.ext.UserExt;
import com.tuzhi.auth.exception.BusinessException;
import com.tuzhi.auth.mapper.ILogLoginMapper;
import com.tuzhi.auth.service.UserService;
import com.tuzhi.auth.util.IpUtils;

/**
 *	和系统有关的操作都在此controller中(包含用户登录,主页面index跳转,获取用户相关信息)
 *  author : codeZ
 *  createdTime: 2018-06-04 13:53:54
 *
 */


@Controller
public class SysCtrl extends BaseCtrl{

	@Autowired
	private UserService userService; 
	@Autowired
	private ILogLoginMapper loginMapper;
	
	@RequestMapping("index")
	public void index(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.sendRedirect(request.getContextPath() + "/index.jsp?"+System.currentTimeMillis());
	}
	
	@PostMapping("admin/login")
	@ResponseBody
	public Res login(String username , String password){
		
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password))
			throw new BusinessException("用户名或密码不能为空!");
		//根据username数据库查询
		UserExt ext = userService.getUserByUsername(username,password);
		session.setAttribute(Constants.SESSION_USER,ext);    //将用户设置到session中
		session.setAttribute(Constants.SESSION_ROLEID, ext.getRoleId());

		//添加登录日志,当然,加日志可以不用事物管理
		LogLogin log = new LogLogin();
		log.setIp(IpUtils.getIP(request));
		log.setUsername(username);
		log.setLoginTime(new Date());
		loginMapper.insert(log);
		
		return Res.success("登录成功!");
	}
	
	@RequestMapping("admin/logout")
	public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//request.getSession().removeAttribute(Constants.SESSION_USER);
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/login.html?"+System.currentTimeMillis());
	}
	
}
