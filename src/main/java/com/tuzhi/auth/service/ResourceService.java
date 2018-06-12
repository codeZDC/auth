package com.tuzhi.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.mapper.IResourceMapper;
import com.tuzhi.auth.domain.Resource;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @ClassName:ResourceService
 * @Description:业务层接口实现类
 * @author 郑德超
 * @CreateDate 2018-06-11 09:53:53
 */
@Service
public class ResourceService {
	
	@Autowired
	private IResourceMapper resourceMapper;
 
	public PageInfo<Resource> findResourceList(Resource resource) {
		// TODO 分页查询
		PageHelper.startPage(resource.getPageNum(), resource.getPageSize());
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(resourceMapper.findResourceList(resource));
		
		return pageInfo;
	}
	
	public Resource getResourceById(Integer id){
		// TODO 根据ID查询
		return resourceMapper.selectByPrimaryKey(id);
	}
	
	public boolean saveResource(Resource resource){
		// TODO 新增
		return resourceMapper.insertSelective(resource) > 0;
	}
	
	public boolean editResource(Resource resource){
		// TODO 修改
		return resourceMapper.updateByPrimaryKeySelective(resource) > 0;
	}
	
	public boolean delResource(List<Integer> resourceArr){
		// TODO	删除
		Example example = new Example(Resource.class);
		Criteria c = example.createCriteria();
		c.andIn("id", resourceArr);
		return resourceMapper.deleteByExample(example) > 0;
	}

	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return resourceMapper.deleteByPrimaryKey(id);
	}

}
