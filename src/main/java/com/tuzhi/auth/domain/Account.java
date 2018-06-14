package com.tuzhi.auth.domain;
/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-13 11:26:51
 *
 */

/**
 * @author TZ
 *
 */
public class Account {

	private String username;
	
	private String password;

	private String id;
	
	private String userId;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
