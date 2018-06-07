package com.tuzhi.auth.mapper;

import java.util.List;

import com.tuzhi.auth.domain.User;

import tk.mybatis.mapper.common.Mapper;

 /**
 * @ClassName:UserMapper
 * @Description:数据层接口
 * @author 郑德超
 * @CreateDate 2018-06-06 15:13:12
 */
public interface IUserMapper extends Mapper<User> {
	
	/**
	 * @title:findUserList
	 * @description: 查询列表
	 * @author 郑德超
	 * @param user
	 * @CreateDate  2018-06-06 15:13:12
	 */
	List<User> findUserList(User user);
	
}
