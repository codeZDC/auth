package com.tuzhi.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.mapper.IRoleMapper;
import com.tuzhi.auth.common.TreeNode;
import com.tuzhi.auth.domain.Role;
import com.tuzhi.auth.domain.User;
import com.tuzhi.auth.exception.BusinessException;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @ClassName:RoleService
 * @Description:业务层接口实现类
 * @author 郑德超
 * @CreateDate 2018-06-11 14:58:03
 */
@Service
public class RoleService {
	
	@Autowired
	private IRoleMapper roleMapper;
 
	public PageInfo<Role> findRoleList(Role role) {
		// TODO 分页查询
		PageHelper.startPage(role.getPageNum(), role.getPageSize());
		PageInfo<Role> pageInfo = new PageInfo<Role>(roleMapper.findRoleList(role));
		
		return pageInfo;
	}
	
	public Role getRoleById(String id){
		// TODO 根据ID查询
		return roleMapper.selectByPrimaryKey(id);
	}
	
	public boolean saveRole(Role role){
		// TODO 新增
		return roleMapper.insertSelective(role) > 0;
	}
	
	public boolean editRole(Role role){
		// TODO 修改
		return roleMapper.updateByPrimaryKeySelective(role) > 0;
	}
	
	public boolean delRole(List<String> roleArr){
		// TODO	删除
		Example example = new Example(Role.class);
		Criteria c = example.createCriteria();
		c.andIn("id", roleArr);
		return roleMapper.deleteByExample(example) > 0;
	}

	public int deleteById(String id) {
		// TODO Auto-generated method stub
		int r = roleMapper.deleteById(id);
		if(r==0)
			throw new BusinessException("不能删除最高权限管理员吗?");
		roleMapper.deleteRoleResources(id);
		return r;
	}

	public List<TreeNode> getRoleResources(String rid) {
		List<TreeNode> nodes = roleMapper.getRoleResources(rid);
		//判断用户是否有权限,,修改checked的值
		for (TreeNode node : nodes) {
			if(node.getRoleId()!=null&&!"-1".equals(node.getRoleId())){
				node.setChecked(true);
				cascadeToChecked(node.getPid(),nodes);
			}
		}
		return roleMapper.getRoleResources(rid);
	}

	//给向上n个父级设置checked=true
	private void cascadeToChecked(Integer pid,List<TreeNode> nodes){
		for (TreeNode node : nodes) {
			if(node.getId().intValue() == pid){
				if(!node.isChecked()){
					node.setChecked(true);
					cascadeToChecked(node.getPid(),nodes);
				}
				return;
			}
		}
	}
	
	
	public void setRoleResources(String rid, Integer[] ids) {
		//首先删除以前的权限
		roleMapper.deleteRoleResources(rid);
		roleMapper.setRoleResources(rid,ids);
		
	}
}
