package com.api.apibackend.Order.Application.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

@Service
public class OrderAddressUpdateUseCase {
    private UpdateOrderService updateOrderService;

    @Autowired
    public OrderAddressUpdateUseCase(UpdateOrderService updateOrderService) {
        this.updateOrderService = updateOrderService;
    }

    public ResponseEntity<OrderEntity> execute(OrderUpdateAddressRequest updateAddressRequest) {
        if (updateAddressRequest != null) {
            return updateOrderService.updateAddressOrder(updateAddressRequest);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicitação de atualização de endereço inválida");
    }
}
