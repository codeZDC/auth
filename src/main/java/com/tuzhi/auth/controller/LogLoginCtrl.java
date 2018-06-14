package com.tuzhi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.LayUiData;
import com.tuzhi.auth.domain.LogLogin;
import com.tuzhi.auth.service.LogLoginService;

/**
 * @ClassName:LogLoginCtrl
 * @Description:的控制器
 * @author 郑德超
 * @CreateDate 2018-06-13 15:55:40
 */
@Controller
@RequestMapping("/log")
public class LogLoginCtrl extends BaseCtrl {

	@Autowired
	private LogLoginService logLoginService;
	
	 /**
	 * @title:findLogLoginList
	 * @description: 分页
	 * @author 郑德超
	 * @param logLogin
	 * @CreateDate  2018-06-13 15:55:40
	 */
	@RequestMapping("login/list")
	@ResponseBody
	public LayUiData findLogLoginList(LogLogin logLogin){
		PageInfo<LogLogin> page = logLoginService.findLogLoginList(logLogin);
		return layUI(page);
	}
	
}
