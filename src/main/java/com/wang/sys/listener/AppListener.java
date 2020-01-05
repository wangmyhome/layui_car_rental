package com.wang.sys.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author shanpeng
 * @ClassName AppListener
 * @description TODO
 * @date 2020/1/5 12:58
 * @Version 1.0
 */
@WebListener
public class AppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //取到ServletContext
        ServletContext context=sce.getServletContext();
        context.setAttribute("base", context.getContextPath());
        System.err.println("---------Servlet容器创建成功 base被放到ServletContext作用域-------");

    }
}
