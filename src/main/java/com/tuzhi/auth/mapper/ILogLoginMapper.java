package com.tuzhi.auth.mapper;

import java.util.List;

import com.tuzhi.auth.domain.LogLogin;

import tk.mybatis.mapper.common.Mapper;

 /**
 * @ClassName:LogLoginMapper
 * @Description:数据层接口
 * @author 郑德超
 * @CreateDate 2018-06-13 15:55:40
 */
public interface ILogLoginMapper extends Mapper<LogLogin> {
	
	/**
	 * @title:findLogLoginList
	 * @description: 查询列表
	 * @author 郑德超
	 * @param logLogin
	 * @CreateDate  2018-06-13 15:55:40
	 */
	List<LogLogin> findLogLoginList(LogLogin logLogin);
	
}
