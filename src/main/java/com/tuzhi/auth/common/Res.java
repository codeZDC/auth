package com.tuzhi.auth.common;
/**
 *
 *  author : codeZ
 *  createdTime: 2018-05-25 11:11:06
 * 	一个同意的ajax返回数据类
 *
 */

public class Res {

	private boolean success;
	
	private Object data;

	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public Res(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Res(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}

	public static Res success(){
		return  new Res(true,"操作成功");
	}
	
	public static Res success(Object data){
		if(data instanceof String){
			return  new Res(true,data.toString());
		}
		return  new Res(true,data);
	}
	public static Res fail(){
		return  new Res(false,null);
	}
	
	public static Res fail(String msg){
		return  new Res(false,msg);
	}
	
}
