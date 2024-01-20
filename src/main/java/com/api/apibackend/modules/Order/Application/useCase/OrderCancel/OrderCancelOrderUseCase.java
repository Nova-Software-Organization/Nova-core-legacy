
/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Order.Application.useCase.OrderCancel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.Domain.service.OrderCancelOrderService;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;

@Service
public class OrderCancelOrderUseCase {
    private OrderCancelOrderService updateOrderService;

    @Autowired
    public OrderCancelOrderUseCase(OrderCancelOrderService updateOrderService) {
        this.updateOrderService = updateOrderService;
    }

    public ResponseEntity<OrderEntity> execute(OrderRequest orderRequest) {
        if (orderRequest != null) {
            return updateOrderService.cancelOrder(orderRequest);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de pedido inválido");
    }
}
