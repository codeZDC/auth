package com.tuzhi.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.LayUiData;
import com.tuzhi.auth.common.Res;
import com.tuzhi.auth.domain.Menu;
import com.tuzhi.auth.service.MenuService;

/**
 * @ClassName:MenuCtrl
 * @Description:的控制器
 * @author 郑德超
 * @CreateDate 2018-06-08 09:06:41
 */
@RestController
@RequestMapping("/menu")
public class MenuCtrl extends BaseCtrl {

	@Autowired
	private MenuService menuService;
	
	 /**
	 * @title:findMenuList
	 * @description: 分页
	 * @author 郑德超
	 * @param menu
	 * @CreateDate  2018-06-08 09:06:41
	 */
	@RequestMapping("/list")
	public LayUiData findMenuList(Menu menu){
		PageInfo<Menu> page = menuService.findMenuList(menu);
		return layUI(page);
	}
	
	/**
	 * @title:getMenuById
	 * @description: 查询一条信息
	 * @author 郑德超
	 * @param id
	 * @CreateDate  2018-06-08 09:06:41
	 */
	@RequestMapping("/get")
	public Menu getMenuById(Integer id){
		Menu menu = menuService.getMenuById(id);
		return menu;
	}
	
	
	 /**
	 * @title:editMenu
	 * @description: 新增
	 * @author 郑德超
	 * @param menu
	 * @CreateDate  2018-06-08 09:06:41
	 */
	@RequestMapping("save")
	public Map<String, Object> saveMenu(Menu menu){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = menuService.saveMenu(menu);
		map.put("success", flag);
		map.put("msg", "新增"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:editMenu
	 * @description: 修改
	 * @author 郑德超
	 * @param menu
	 * @CreateDate  2018-06-08 09:06:41
	 */
	@RequestMapping("edit")
	public Map<String, Object> editMenu(Menu menu){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = menuService.editMenu(menu);
		map.put("success", flag);
		map.put("msg", "修改"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:delMenu
	 * @description: 删除
	 * @author 郑德超
	 * @param ids	主键ID集合
	 * @CreateDate  2018-06-08 09:06:41
	 */
	@RequestMapping("del")
	public Map<String, Object> delMenu(@RequestParam(value = "ids[]",required = false,defaultValue = "") List<Integer> ids){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = menuService.delMenu(ids);
		map.put("success", flag);
		map.put("msg", "删除"+(flag?"成功":"失败"));
		return map;
	}
	
	//菜单页面下拉框获取父级菜单信息(父级菜单的pid为0)
	@GetMapping("/admin/getMenusByPid")
	public Res getMenusByPid(Integer pid){
		
		return Res.success(menuService.getMenusByPid(pid));
	}
	
	//获取二级菜单或没有子菜单的菜单
	@GetMapping("/admin/getSecondMenus")
	public Res getSecondMenus(){
		
		return Res.success(menuService.getSecondMenus());
	}
	
	@PostMapping("deleteById")
	public Res deleteById(Integer id){
		
		return Res.success(menuService.deleteById(id)>0?"删除成功!":"网络错误!");
	}
	
	
}
