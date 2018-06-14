package com.tuzhi.auth.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.domain.Account;
import com.tuzhi.auth.domain.User;
import com.tuzhi.auth.domain.ext.UserExt;
import com.tuzhi.auth.exception.BusinessException;
import com.tuzhi.auth.mapper.ILogLoginMapper;
import com.tuzhi.auth.mapper.IUserMapper;
import com.tuzhi.auth.util.Md5Utils;
import com.tuzhi.auth.util.StringUtils;
import com.tuzhi.auth.util.UUID;

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
	
	public boolean saveUser(User user,Account account,String roleId){
		// TODO 新增
		String id = UUID.get();
		user.setId(id);
		user.setRegisttime(new Date());
		//添加账号
		if(StringUtils.hasEmpty(account.getUsername(),account.getPassword()))
			throw new BusinessException("账号或密码为空!");
		account.setId(UUID.get());
		account.setUserId(id);
		account.setPassword(Md5Utils.encode(account.getPassword()));
		userMapper.insertSelective(user);
		//关联用户角色
		userMapper.linkRole(id,roleId);
		return  userMapper.addAccount(account)> 0;
	}
	
	public boolean editUser(User user,Account account,String roleId){
		String userId = user.getId();
		// TODO 修改
		//1更新账号密码
		if(StringUtils.hasEmpty(account.getUsername()))
			throw new BusinessException("账号不能为空!");
		account.setUserId(userId);
		userMapper.updateAccount(account);
		//更新角色
		userMapper.updateLinedRole(userId,roleId);
		return userMapper.updateByPrimaryKeySelective(user) > 0;
	}
	
	public boolean delUser(List<String> userArr){
		// TODO	删除
		Example example = new Example(User.class);
		Criteria c = example.createCriteria();
		c.andIn("id", userArr);
		return userMapper.deleteByExample(example) > 0;
	}

	public UserExt getUserByUsername(String username,String password) {
		// TODO Auto-generated method stub
		UserExt ext = userMapper.getUserByUsername(username);
		if(ext == null || !Md5Utils.match(password, ext.getPassword()))
			throw new BusinessException("用户名或密码不正确!");
		return ext;
	}

	public int deleteById(String id) {
		// TODO Auto-generated method stub
		//先删除账号,然后删除用户
		userMapper.deleteAccountByUserId(id);
		//删除用户角色
		userMapper.cancelRole(id);
		return userMapper.deleteByPrimaryKey(id);
	}

	public int repassword(String username, String password, String newPassword) {
		// TODO Auto-generated method stub
		if(password.equals(newPassword))
			throw new BusinessException("新密码和旧密码一致!");
		int i = userMapper.repassword(username,Md5Utils.encode(password),Md5Utils.encode(newPassword));
		if(i==0)
			throw new BusinessException("旧密码输入错误!");
		return i;
	}

}
