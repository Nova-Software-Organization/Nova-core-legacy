package com.api.apibackend.Order.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.api.apibackend.Order.Domain.middleware.InteceptorOrder;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
    
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InteceptorOrder())
                .addPathPatterns("/order");
    }
}
