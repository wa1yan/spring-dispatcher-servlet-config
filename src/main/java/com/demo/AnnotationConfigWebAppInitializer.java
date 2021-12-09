package com.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class AnnotationConfigWebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();

        //set to SpringContainer
        webApplicationContext.setServletContext(container);
        webApplicationContext.register(AppConfig.class);
        webApplicationContext.refresh();

        ServletRegistration.Dynamic disptacher = container
                .addServlet("dispatcher",new DispatcherServlet(webApplicationContext));

        disptacher.setLoadOnStartup(1);
        disptacher.addMapping("/*");
    }
}
