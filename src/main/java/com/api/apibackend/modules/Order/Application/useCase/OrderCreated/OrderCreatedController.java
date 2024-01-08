/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Order.Application.useCase.OrderCreated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.apibackend.modules.Order.Application.DTOs.CreateOrderRequest;
import com.api.apibackend.modules.Order.infra.service.OrderCircuitBreaker;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("v1/pedido")
public class OrderCreatedController {
    private OrderCreatedUseCase orderRequestManagerUseCase;
    private OrderCircuitBreaker orderCircuitBreaker;

    @Autowired
    public OrderCreatedController(OrderCreatedUseCase orderRequestManagerUseCase,
            OrderCircuitBreaker orderCircuitBreaker) {
        this.orderRequestManagerUseCase = orderRequestManagerUseCase;
        this.orderCircuitBreaker = orderCircuitBreaker;
    }

    @PostMapping("/criar")
    @CircuitBreaker(name = "criarpedido", fallbackMethod = "fallbackCreateOrder")
    @PreAuthorize("hasRole('USER')")
    @Tag(name = "criar pedido", description = "Responsavel por criar um pedido feito pelo cliente")
    @Operation(summary = "Rota tem como objetivo criar fazer o pedido do cliente e validar todas as situações possiveis antes de contabilizar no sistema a compra!")
    public ResponseEntity<String> handle(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderCircuitBreaker.executeCreateOrder(createOrderRequest, () -> {
            ResponseEntity<String> orderResponse = orderRequestManagerUseCase.execute(createOrderRequest);

            if (orderResponse.getStatusCode().is2xxSuccessful()) {
                return new ResponseEntity<>(orderResponse.getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(orderResponse.getBody(), orderResponse.getStatusCode());
            }
        });
    }
}
