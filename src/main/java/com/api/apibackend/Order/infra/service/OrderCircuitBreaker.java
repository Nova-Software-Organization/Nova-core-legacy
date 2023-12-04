package com.api.apibackend.Order.infra.service;

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.apibackend.Order.Domain.model.CreateOrderRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class OrderCircuitBreaker {
    
    @CircuitBreaker(name = "criarpedido", fallbackMethod = "fallbackCreateOrder")
    public ResponseEntity<String> executeCreateOrder(CreateOrderRequest createOrderRequest, Supplier<ResponseEntity<String>> supplier) {
        return supplier.get();
    }

    public ResponseEntity<String> fallbackCreateOrder(CreateOrderRequest createOrderRequest, Throwable t) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na solicitação: " + t.getMessage());
    }
}
