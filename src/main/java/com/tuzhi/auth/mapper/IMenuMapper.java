package com.tuzhi.auth.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tuzhi.auth.common.MenuNode;
import com.tuzhi.auth.domain.Menu;

import tk.mybatis.mapper.common.Mapper;

 /**
 * @ClassName:MenuMapper
 * @Description:数据层接口
 * @author 郑德超
 * @CreateDate 2018-06-08 09:06:41
 */
public interface IMenuMapper extends Mapper<Menu> {
	
	/**
	 * @title:findMenuList
	 * @description: 查询列表
	 * @author 郑德超
	 * @param menu
	 * @CreateDate  2018-06-08 09:06:41
	 */
	List<Menu> findMenuList(Menu menu);
	
	List<Map> getMenusByPid(@Param("pid") Integer pid);

	List<Map> getSecondMenus();

	List<MenuNode> getHomeMenu(String roleId);
	
}
