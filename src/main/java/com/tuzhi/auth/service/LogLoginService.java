package com.tuzhi.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.domain.LogLogin;
import com.tuzhi.auth.mapper.ILogLoginMapper;

/**
 * @ClassName:LogLoginService
 * @Description:业务层接口实现类
 * @author 郑德超
 * @CreateDate 2018-06-13 15:55:40
 */
@Service
public class LogLoginService {
	
	@Autowired
	private ILogLoginMapper logLoginMapper;
 
	public PageInfo<LogLogin> findLogLoginList(LogLogin logLogin) {
		// TODO 分页查询
		PageHelper.startPage(logLogin.getPageNum(), logLogin.getPageSize());
		PageInfo<LogLogin> pageInfo = new PageInfo<LogLogin>(logLoginMapper.findLogLoginList(logLogin));
		
		return pageInfo;
	}
}
