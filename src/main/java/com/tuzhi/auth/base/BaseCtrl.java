package com.tuzhi.auth.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.common.LayUiData;

/**
 * 父类控制器
 * @author codeZ
 *
 * 2017年3月15日 下午2:14:24
 */
@SuppressWarnings("deprecation")
public class BaseCtrl {
	public static final String USER_SESSION = "USER_SESSION";	//PC session
	public final String WECHATUSER = "WECHATUSER";		//微信 session
	protected static ObjectMapper mapper = new ObjectMapper();
	protected static JsonFactory factory = mapper.getJsonFactory();
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	public LayUiData layUI(PageInfo<?> info){
		LayUiData data = new  LayUiData();
		data.setData(info.getList());
		data.setCount(info.getTotal());

		return data ;
	}
}
