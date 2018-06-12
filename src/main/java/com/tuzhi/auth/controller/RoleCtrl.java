package com.tuzhi.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.LayUiData;
import com.tuzhi.auth.common.Res;
import com.tuzhi.auth.domain.Role;
import com.tuzhi.auth.service.RoleService;

/**
 * @ClassName:RoleCtrl
 * @Description:的控制器
 * @author 郑德超
 * @CreateDate 2018-06-11 14:58:03
 */
@Controller
@RequestMapping("/role")
public class RoleCtrl extends BaseCtrl {

	@Autowired
	private RoleService roleService;
	
	 /**
	 * @title:findRoleList
	 * @description: 分页
	 * @author 郑德超
	 * @param role
	 * @CreateDate  2018-06-11 14:58:03
	 */
	@RequestMapping("/list")
	@ResponseBody
	public LayUiData findRoleList(Role role){
		PageInfo<Role> page = roleService.findRoleList(role);
		return layUI(page);
	}
	
	/**
	 * @title:getRoleById
	 * @description: 查询一条信息
	 * @author 郑德超
	 * @param id
	 * @CreateDate  2018-06-11 14:58:03
	 */
	@RequestMapping("/get")
	@ResponseBody
	public Role getRoleById(String id){
		Role role = roleService.getRoleById(id);
		return role;
	}
	
	
	 /**
	 * @title:editRole
	 * @description: 新增
	 * @author 郑德超
	 * @param role
	 * @CreateDate  2018-06-11 14:58:03
	 */
	@RequestMapping("save")
	@ResponseBody
	public Map<String, Object> saveRole(Role role){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = roleService.saveRole(role);
		map.put("success", flag);
		map.put("msg", "新增"+(flag?"成功":"失败"));
		System.err.println(role.getId());
		return map;
	}
	
	
	 /**
	 * @title:editRole
	 * @description: 修改
	 * @author 郑德超
	 * @param role
	 * @CreateDate  2018-06-11 14:58:03
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Map<String, Object> editRole(Role role){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = roleService.editRole(role);
		map.put("success", flag);
		map.put("msg", "修改"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:delRole
	 * @description: 删除
	 * @author 郑德超
	 * @param ids	主键ID集合
	 * @CreateDate  2018-06-11 14:58:03
	 */
	@RequestMapping("del")
	@ResponseBody
	public Map<String, Object> delRole(@RequestParam(value = "ids[]",required = false,defaultValue = "") List<String> ids){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = roleService.delRole(ids);
		map.put("success", flag);
		map.put("msg", "删除"+(flag?"成功":"失败"));
		return map;
	}
	
	//根据主键删除
	@PostMapping("deleteById")
	@ResponseBody
	public Res deleteById(String id){
		return Res.success(roleService.deleteById(id)>0);
	}
	
	//根据用户获取用户的
	@GetMapping("admin/getRoleResources")
	@ResponseBody
	public Res getRoleResources(String rid){
		
		return Res.success(roleService.getRoleResources(rid));
	}
	
	@PostMapping("setRoleResources")
	@ResponseBody
	public Res setRoleResources(String rid , @RequestParam("ids[]")Integer[] ids){
		roleService.setRoleResources(rid,ids);
		return Res.success();
	}
	
}
