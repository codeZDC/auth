package com.tuzhi.auth.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.mapper.IUserMapper;
import com.tuzhi.auth.common.MenuNode;
import com.tuzhi.auth.domain.User;
import com.tuzhi.auth.domain.ext.UserExt;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @ClassName:UserService
 * @Description:业务层接口实现类
 * @author 郑德超
 * @CreateDate 2018-06-06 15:13:12
 */
@Service
public class UserService {
	
	@Autowired
	private IUserMapper userMapper;
 
	public PageInfo<User> findUserList(User user) {
		// TODO 分页查询
		PageHelper.startPage(user.getPageNum(), user.getPageSize());
		PageInfo<User> pageInfo = new PageInfo<User>(userMapper.findUserList(user));
		
		return pageInfo;
	}
	
	public User getUserById(String Id){
		// TODO 根据ID查询
		return userMapper.selectByPrimaryKey(Id);
	}
	
	public boolean saveUser(User user){
		// TODO 新增
		user.setRegisttime(new Date());
		return userMapper.insertSelective(user) > 0;
	}
	
	public boolean editUser(User user){
		// TODO 修改
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}
	
	public boolean delUser(List<String> userArr){
		// TODO	删除
		Example example = new Example(User.class);
		Criteria c = example.createCriteria();
		c.andIn("id", userArr);
		return userMapper.deleteByExample(example) > 0;
	}

	public UserExt getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUsername(username);
	}

}
