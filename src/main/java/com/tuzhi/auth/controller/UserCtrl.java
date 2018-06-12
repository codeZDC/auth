package com.tuzhi.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.LayUiData;
import com.tuzhi.auth.domain.User;
import com.tuzhi.auth.service.UserService;

/**
 * @ClassName:UserController
 * @Description:的控制器
 * @author 郑德超
 * @CreateDate 2018-06-06 15:13:12
 */
@Controller
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
	@ResponseBody
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
	@ResponseBody
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
	@ResponseBody
	public Map<String, Object> saveUser(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.saveUser(user);
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
	@ResponseBody
	public Map<String, Object> editUser(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.editUser(user);
		map.put("success", flag);
		map.put("msg", "修改"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:delUser
	 * @description: 删除
	 * @author 郑德超
	 * @param ids	主键ID集合
	 * @CreateDate  2018-06-06 15:13:12
	 */
	@RequestMapping("del")
	@ResponseBody
	public Map<String, Object> delUser(@RequestParam(value = "ids[]",required = false,defaultValue = "") List<String> ids){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = userService.delUser(ids);
		map.put("success", flag);
		map.put("msg", "删除"+(flag?"成功":"失败"));
		return map;
	}
}
