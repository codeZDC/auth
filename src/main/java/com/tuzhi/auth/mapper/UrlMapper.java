package com.tuzhi.auth.mapper;
/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-07 10:11:27
 *
 */

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface UrlMapper {
	
	@Select("SELECT resource FROM t_user_resource  WHERE user_id = 1")
	List<String> getPermissions();
	
	
}
