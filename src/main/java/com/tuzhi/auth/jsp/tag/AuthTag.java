package com.tuzhi.auth.jsp.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tuzhi.auth.common.Constants;
import com.tuzhi.auth.mapper.IResourceMapper;

/**
 *
 *  author : codeZ //菜单权限自定义标签
 *  createdTime: 2018-06-12 14:06:22
 *
 */

public class AuthTag extends SimpleTagSupport{

	private String value;
	
	public void setValue(String value) {
		this.value = value;
	}



	@Override
	public void doTag() throws JspException, IOException {
		JspFragment body = this.getJspBody();
		PageContext pageContext = (PageContext) body.getJspContext();
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		IResourceMapper resourceMapper = context.getBean(IResourceMapper.class);
		List<String> permissions = resourceMapper.getPermissions((String)pageContext.getSession().getAttribute(Constants.SESSION_ROLEID));

		for (String string : permissions) {
			if(value.equals(string)){
				body.invoke(null);
				return;
			}
		}
	}
	
	
}
