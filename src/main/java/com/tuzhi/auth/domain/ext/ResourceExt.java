package com.tuzhi.auth.domain.ext;

import com.tuzhi.auth.domain.Resource;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-11 10:12:57
 *
 */

public class ResourceExt extends Resource{

	//资源列表中需要的所属菜单名称
	private String pmenuName;

	public String getPmenuName() {
		return pmenuName;
	}

	public void setPmenuName(String pmenuName) {
		this.pmenuName = pmenuName;
	}
	
	
	
}
