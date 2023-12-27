package com.api.apibackend.Order.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.Order.Application.DTOs.CreateOrderRequest;
import com.api.apibackend.Order.Domain.service.OrderCreationService;
import com.api.apibackend.Order.Domain.service.UpdateOrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderRequestManagerUseCase {
    private OrderCreationService orderCreationService;
    private UpdateOrderService updateOrderService;

    @Autowired
    public OrderRequestManagerUseCase(OrderCreationService orderCreationService, UpdateOrderService updateOrderService) {
        this.orderCreationService = orderCreationService;
        this.updateOrderService = updateOrderService;
    }

    public ResponseEntity<String> executeRequestManage(CreateOrderRequest createOrderRequest) {
        try {
            return orderCreationService.createOrder(
                    createOrderRequest.getOrderRequest(),
                    createOrderRequest.getCustomerAddress(),
                    createOrderRequest.getClientRequest()
            );
        } catch (IllegalAccessError ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na solicitação: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor: " + ex.getMessage(), ex);
        }
    }
}
