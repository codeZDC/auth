package com.tuzhi.auth.domain.ext;

import com.tuzhi.auth.domain.User;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-07 16:37:12
 *
 */

//用户扩展类,因为以前的user信息不全,主要用户登录的时候保存用户的信息,以备后用
public class UserExt extends User{
	
	//角色id
	private String roleId;
	//角色名称
	private String roleName;
	//用户账号
	private String account;
	//用户密码
	private String password;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

