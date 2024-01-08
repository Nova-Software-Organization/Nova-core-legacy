package com.api.apibackend.modules.Order.Application.useCase.OrderCreated;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.modules.Order.Application.DTOs.CreateOrderRequest;
import com.api.apibackend.modules.Order.Domain.service.OrderCreationService;
import com.api.apibackend.modules.Order.Domain.service.UpdateOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreatedUseCase {
    private OrderCreationService orderCreationService;
    private UpdateOrderService updateOrderService;

    @Autowired
    public OrderCreatedUseCase(OrderCreationService orderCreationService, UpdateOrderService updateOrderService) {
        this.orderCreationService = orderCreationService;
        this.updateOrderService = updateOrderService;
    }

    public ResponseEntity<String> execute(CreateOrderRequest createOrderRequest) {
        try {
            return orderCreationService.createOrder(
                    createOrderRequest.getOrderRequest(),
                    createOrderRequest.getCustomerAddress(),
                    createOrderRequest.getClientRequest());
        } catch (IllegalAccessError ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na solicitação: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro interno do servidor: " + ex.getMessage(), ex);
        }
    }
}
