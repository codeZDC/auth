package com.tuzhi.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.Constants;
import com.tuzhi.auth.common.LayUiData;
import com.tuzhi.auth.common.Res;
import com.tuzhi.auth.domain.Account;
import com.tuzhi.auth.domain.User;
import com.tuzhi.auth.domain.ext.UserExt;
import com.tuzhi.auth.exception.BusinessException;
import com.tuzhi.auth.service.UserService;

/**
 * @ClassName:UserController
 * @Description:的控制器
 * @author 郑德超
 * @CreateDate 2018-06-06 15:13:12
 */
@RestController
@RequestMapping("/user")
public class UserCtrl extends BaseCtrl {

	@Autowired
	private UserService userService;
	
	 /**
	 * @title:findUserList
	 * @description: 分页
	 * @author 郑德超
	 * @param user
	 * @CreateDate  2018-06-06 15:13:12
	 */
	@RequestMapping("/list")
	public LayUiData findUserList(User user){
		PageInfo<User> page = userService.findUserList(user);
		return layUI(page);
	}
	
	/**
	 * @title:getUserById
	 * @description: 查询一条信息
	 * @author 郑德超
	 * @param Id
	 * @CreateDate  2018-06-06 15:13:12
	 */
	@RequestMapping("/get")
	public User getUserById(String Id){
		User user = userService.getUserById(Id);
		return user;
	}
	
	
	 /**
	 * @title:editUser
	 * @description: 新增
	 * @author 郑德超
	 * @param user
	 * @CreateDate  2018-06-06 15:13:12
	 */
	@RequestMapping("save")
	public Map<String, Object> saveUser(User user,Account account,String roleId){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.saveUser(user,account,roleId);
		map.put("success", flag);
		map.put("msg", "新增"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:editUser
	 * @description: 修改
	 * @author 郑德超
	 * @param user
	 * @CreateDate  2018-06-06 15:13:12
	 */
	@RequestMapping("edit")
	public Map<String, Object> editUser(User user,Account account,String roleId){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.editUser(user,account,roleId);
		map.put("success", flag);
		map.put("msg", "修改"+(flag?"成功":"失败"));
		return map;
	}
	
	//根据id删除用户
	@PostMapping("deleteById")
	public Res deleteById(String id){
		int i = userService.deleteById(id);
		if(i<1)
			throw new BusinessException("操作不当,没有删除相应的用户!");
		return Res.success();
	}

	@PostMapping("admin/repassword")
	public Res repassword(String password,String newPassword){
		UserExt ext = (UserExt)session.getAttribute(Constants.SESSION_USER);
		userService.repassword(ext.getUsername(), password,newPassword);
		return Res.success("密码修改成功");
	}
}
