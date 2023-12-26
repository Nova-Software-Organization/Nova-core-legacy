package com.api.apibackend.Order.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.Order.Application.DTOs.CreateOrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.Order.Domain.service.OrderCreationService;
import com.api.apibackend.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Order.infra.entity.OrderEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderUseCase {

    @Autowired
    private OrderCreationService orderService;

    @Autowired
    private UpdateOrderService updateOrderService;

    public ResponseEntity<String> executeRequestManage(CreateOrderRequest createOrderRequest) {
        try {
            return orderService.createOrder(createOrderRequest.getOrderRequest(),
                    createOrderRequest.getCustomerAddress(), createOrderRequest.getClientRequest());
        } catch (IllegalAccessError ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro na solicitação: " + ex.getMessage(), ex);

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro interno do servidor: " + ex.getMessage(), ex);
        }
    }

    public ResponseEntity<OrderEntity> executeCanceladOrder(OrderRequest numberOrder) {
        if (numberOrder != null) {
            return updateOrderService.canceladOrder(numberOrder);
        }

        return null;
    }

    public ResponseEntity<OrderEntity> executeAddress(OrderUpdateAddressRequest numberOrder) {
        if (numberOrder != null) {
            return updateOrderService.updateAddressOrder(numberOrder);
        }

        return null;
    }
}
