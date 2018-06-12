package com.tuzhi.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.base.BaseCtrl;
import com.tuzhi.auth.common.LayUiData;
import com.tuzhi.auth.common.Res;
import com.tuzhi.auth.domain.Resource;
import com.tuzhi.auth.service.ResourceService;

/**
 * @ClassName:ResourceCtrl
 * @Description:的控制器
 * @author 郑德超
 * @CreateDate 2018-06-11 09:53:53
 */
@Controller
@RequestMapping("/resource")
public class ResourceCtrl extends BaseCtrl {

	@Autowired
	private ResourceService resourceService;
	
	 /**
	 * @title:findResourceList
	 * @description: 分页
	 * @author 郑德超
	 * @param resource
	 * @CreateDate  2018-06-11 09:53:53
	 */
	@RequestMapping("/list")
	@ResponseBody
	public LayUiData findResourceList(Resource resource){
		PageInfo<Resource> page = resourceService.findResourceList(resource);
		return layUI(page);
	}
	
	/**
	 * @title:getResourceById
	 * @description: 查询一条信息
	 * @author 郑德超
	 * @param id
	 * @CreateDate  2018-06-11 09:53:53
	 */
	@RequestMapping("/get")
	@ResponseBody
	public Resource getResourceById(Integer id){
		Resource resource = resourceService.getResourceById(id);
		return resource;
	}
	
	
	 /**
	 * @title:editResource
	 * @description: 新增
	 * @author 郑德超
	 * @param resource
	 * @CreateDate  2018-06-11 09:53:53
	 */
	@RequestMapping("save")
	@ResponseBody
	public Map<String, Object> saveResource(Resource resource){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = resourceService.saveResource(resource);
		map.put("success", flag);
		map.put("msg", "新增"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:editResource
	 * @description: 修改
	 * @author 郑德超
	 * @param resource
	 * @CreateDate  2018-06-11 09:53:53
	 */
	@RequestMapping("edit")
	@ResponseBody
	public Map<String, Object> editResource(Resource resource){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = resourceService.editResource(resource);
		map.put("success", flag);
		map.put("msg", "修改"+(flag?"成功":"失败"));
		return map;
	}
	
	
	 /**
	 * @title:delResource
	 * @description: 删除
	 * @author 郑德超
	 * @param ids	主键ID集合
	 * @CreateDate  2018-06-11 09:53:53
	 */
	@RequestMapping("del")
	@ResponseBody
	public Map<String, Object> delResource(@RequestParam(value = "ids[]",required = false,defaultValue = "") List<Integer> ids){
		Map<String, Object> map = new HashMap<String, Object>();
		boolean flag = resourceService.delResource(ids);
		map.put("success", flag);
		map.put("msg", "删除"+(flag?"成功":"失败"));
		return map;
	}
	
	//根据id删除资源
	@PostMapping("deleteById")
	@ResponseBody
	public Res deleteById(Integer id){
		
		return Res.success(resourceService.deleteById(id)>0?"删除成功!":"网络错误!");
	}
	
}
