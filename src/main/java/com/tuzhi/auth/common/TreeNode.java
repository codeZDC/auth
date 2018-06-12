package com.tuzhi.auth.common;
/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-11 16:07:38
 *  id: 1,
		pId: 0,
		name: "移动编目",
		open: false,
		
 *		权限配置需要的树结构
 */

public class TreeNode {
	
	private Integer id;
	
	private Integer pid;
	
	private String name;
	
	private boolean open = false;
	
	private boolean checked;

	private  String roleId;
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
