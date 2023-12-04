package com.api.apibackend.Order.Domain.middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.api.apibackend.Order.Domain.handler.OrderRequestControllerHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InteceptorOrder implements HandlerInterceptor {
    
    @Autowired
    public OrderRequestControllerHandler orderRequestControllerHandler;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        orderRequestControllerHandler.checkout(null, null, null);
        return true;
    }
}
