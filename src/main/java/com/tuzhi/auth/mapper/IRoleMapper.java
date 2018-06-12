package com.tuzhi.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.tuzhi.auth.common.TreeNode;
import com.tuzhi.auth.domain.Role;

import tk.mybatis.mapper.common.Mapper;

 /**
 * @ClassName:RoleMapper
 * @Description:数据层接口
 * @author 郑德超
 * @CreateDate 2018-06-11 14:58:03
 */
public interface IRoleMapper extends Mapper<Role> {
	
	/**
	 * @title:findRoleList
	 * @description: 查询列表
	 * @author 郑德超
	 * @param role
	 * @CreateDate  2018-06-11 14:58:03
	 */
	List<Role> findRoleList(Role role);

	List<TreeNode> getRoleResources(String rid);

	int setRoleResources(String rid, Integer[] ids);
	
	@Delete("delete from t_role_resource where role_id = #{zdc}")
	int deleteRoleResources(String rid);

	@Delete("delete from role where id = #{id} and deletable!=0 ")
	int deleteById(String id);
	
}
