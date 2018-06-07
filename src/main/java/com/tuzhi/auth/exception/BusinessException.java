package com.tuzhi.auth.exception;

/**
 *	业务异常类
 *  author : codeZ
 *  createdTime: 2018-05-21 15:33:35
 *
 */

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BusinessException(Object obj){
		super(obj.toString());
	}
	
}
