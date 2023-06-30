package com.coolbook.erp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class RequestInterceptorAppConfig extends WebMvcConfigurerAdapter {
    @Autowired
    RequestInterceptor productServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(productServiceInterceptor).excludePathPatterns("/initialSetup", "/swagger-ui.html", "/configuration/ui", "/swagger-resources/**", "/**/api-docs",
                "/health", "/hystrix.stream", "/version","/downloadFile/**");
    }
}
