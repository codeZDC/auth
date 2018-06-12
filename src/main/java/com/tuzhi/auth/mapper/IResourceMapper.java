package com.tuzhi.auth.mapper;

import java.util.List;

import com.tuzhi.auth.domain.Resource;

import tk.mybatis.mapper.common.Mapper;

 /**
 * @ClassName:ResourceMapper
 * @Description:数据层接口
 * @author 郑德超
 * @CreateDate 2018-06-11 09:53:53
 */
public interface IResourceMapper extends Mapper<Resource> {
	
	/**
	 * @title:findResourceList
	 * @description: 查询列表
	 * @author 郑德超
	 * @param resource
	 * @CreateDate  2018-06-11 09:53:53
	 */
	List<Resource> findResourceList(Resource resource);

	List<String> getPermissions(String roleId);

	int deleteRoleResources(String rid);
	
}
