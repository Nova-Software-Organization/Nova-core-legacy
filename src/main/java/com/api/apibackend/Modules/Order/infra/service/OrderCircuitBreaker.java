package com.api.apibackend.Modules.Order.infra.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.apibackend.Modules.Order.Application.DTOs.CreateOrderRequest;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderCircuitBreaker {
    @CircuitBreaker(name = "criarpedido", fallbackMethod = "fallbackCreateOrder")
    public ResponseEntity<String> executeCreateOrder(CreateOrderRequest createOrderRequest, Supplier<ResponseEntity<String>> supplier) {
        log.info("Pedido foi direcionado para para circuit breaker");
        return supplier.get();
    }

    public ResponseEntity<String> fallbackCreateOrder(CreateOrderRequest createOrderRequest, Throwable t) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na solicitação: " + t.getMessage());
    }
}
