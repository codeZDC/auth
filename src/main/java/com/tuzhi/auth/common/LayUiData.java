package com.tuzhi.auth.common;
/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-06 15:46:17
 *
 */

import java.util.List;

public class LayUiData {

	private Integer code = 0;
	private String msg = "";
	private long count;
	private List<?> data;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	
}
