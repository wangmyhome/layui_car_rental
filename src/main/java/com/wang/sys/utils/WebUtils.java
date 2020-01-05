package com.wang.sys.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author shanpeng
 * @ClassName WebUtils
 * @description TODO
 * @date 2020/1/5 13:11
 * @Version 1.0
 */
public class WebUtils {
    public static ServletRequestAttributes getServletRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 得到当前线程的请求对象
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }

    /**
     * 得到当前线程的响应对象
     */
    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 得到session对象
     */
    public static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    /**
     * 得到servletContext对象
     */
    public static String getServletContext() {
        return getHttpServletRequest().getServletPath();
    }

}
