package com.tuzhi.auth.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tuzhi.auth.mapper.UrlMapper;

/**
 *
 *  author : codeZ
 *  createdTime: 2018-06-07 10:59:50
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration(locations={"classpath:spring.xml"}) //加载配置文件 
public class TestCache {

	@Autowired
	private UrlMapper mapper;
	
	@Test
	public void cache(){
		mapper.getPermissions(null);
	}
	
}
