package com.tuzhi.auth.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuzhi.auth.mapper.IMenuMapper;
import com.tuzhi.auth.common.MenuNode;
import com.tuzhi.auth.domain.Menu;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @ClassName:MenuService
 * @Description:业务层接口实现类
 * @author 郑德超
 * @CreateDate 2018-06-08 09:06:41
 */
@Service
public class MenuService {
	
	@Autowired
	private IMenuMapper menuMapper;
 
	public PageInfo<Menu> findMenuList(Menu menu) {
		// TODO 分页查询
		PageHelper.startPage(menu.getPageNum(), menu.getPageSize());
		PageInfo<Menu> pageInfo = new PageInfo<Menu>(menuMapper.findMenuList(menu));
		
		return pageInfo;
	}
	
	public Menu getMenuById(Integer id){
		// TODO 根据ID查询
		return menuMapper.selectByPrimaryKey(id);
	}
	
	public boolean saveMenu(Menu menu){
		// TODO 新增
		//排序问题,将子菜单排序加前缀,前缀为父级id
		return menuMapper.insertSelective(menu) > 0;
	}
	
	public boolean editMenu(Menu menu){
		// TODO 修改
		return menuMapper.updateByPrimaryKeySelective(menu) > 0;
	}
	
	public boolean delMenu(List<Integer> menuArr){
		// TODO	删除
		Example example = new Example(Menu.class);
		Criteria c = example.createCriteria();
		c.andIn("id", menuArr);
		return menuMapper.deleteByExample(example) > 0;
	}

	public List<Map> getMenusByPid(Integer pid) {
		// TODO Auto-generated method stub
		return menuMapper.getMenusByPid(pid);
	}
	public List<Map> getSecondMenus() {
		// TODO Auto-generated method stub
		return menuMapper.getSecondMenus();
	}

	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return menuMapper.deleteByPrimaryKey(id);
	}
}
