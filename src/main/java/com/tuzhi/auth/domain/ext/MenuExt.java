package com.tuzhi.auth.domain.ext;

import com.tuzhi.auth.domain.Menu;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-08 15:14:46
 *
 */

public class MenuExt extends Menu{

	//展示菜单的时候需要的父级菜单名称
	private String pname;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
	
	
}
