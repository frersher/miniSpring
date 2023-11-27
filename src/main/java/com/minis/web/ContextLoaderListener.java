package com.minis.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * desc
 *
 * @author: chenb
 * @date: 2023/9/6
 **/
public class ContextLoaderListener implements ServletContextListener {
    public static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";
    private WebApplicationContext context;



    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void initWebApplicationContext(ServletContext servletContext) {
        String sContextLocation =
                servletContext.getInitParameter(CONTEXT_CONFIG_LOCATION);
        WebApplicationContext wac = new
                AnnotationConfigWebApplicationContext(sContextLocation);
        wac.setServletContext(servletContext);
        this.context = wac;
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ ATTRIBUTE, this.context);
    }
}