package com.tuzhi.auth.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.support.json.JSONUtils;

/**
 *	全局异常处理类
 *  author : codeZ
 *  createdTime: 2018-05-21 15:30:24
 *
 */

public class GlobalExceptionHandler implements HandlerExceptionResolver{

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
            HttpServletResponse response, Object object, Exception exception) {
        // 判断是否ajax请求
		
        logger.error(exception.getMessage(),exception);
		
        if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
            // 如果不是ajax，JSP格式返回
            // 为安全起见，只有业务异常我们对前端可见，否则否则统一归为系统异常
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("success", false);
            if (exception instanceof BusinessException) {
                map.put("msg", exception.getMessage());
            } else {
                map.put("msg", "网络异常,请稍后重试  ..");
            }
            exception.printStackTrace();
            //对于非ajax请求，我们都统一跳转到error.jsp页面
            return new ModelAndView("../../error", map);
        } else {
            // 如果是ajax请求，JSON格式返回
            try {
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("success", false);
                // 为安全起见，只有业务异常我们对前端可见，否则统一归为系统异常
                if (exception instanceof BusinessException) {
                    map.put("msg", exception.getMessage());
                } else {
                    map.put("msg", "网络异常,请稍后重试  ..");
                }
                writer.write(JSONUtils.toJSONString(map));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
