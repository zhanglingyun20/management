package com.management.controller.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.management.model.Users;
  
  
/**
 *   
 * <b>Descriptions:</b>
 * <p></p>
 * @author sawyer
 * @createDate 2016年8月6日
 * @file UserInterceptor.java
 * @package com.management.controller.common
 * @project management
 * @version 1.0
 *
 */
public class UserInterceptor extends HandlerInterceptorAdapter{  
    private final Logger log = LoggerFactory.getLogger(UserInterceptor.class);  
    @Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {    
        Users user =  (Users)request.getSession().getAttribute("user");   
        String servletPath=request.getServletPath();
        log.info(servletPath);
        if(user == null){  
        	response.sendRedirect(request.getContextPath());  
            return false;  
        }
        return true; 
    }    
    
    /** 
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
    }    
    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
    }    
  
}    