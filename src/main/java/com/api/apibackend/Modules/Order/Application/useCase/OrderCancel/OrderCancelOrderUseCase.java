
/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Order.Application.useCase.OrderCancel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.apibackend.Modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Modules.Order.Domain.service.UpdateOrderService;
import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;

@Service
public class OrderCancelOrderUseCase {
    private UpdateOrderService updateOrderService;

    @Autowired
    public OrderCancelOrderUseCase(UpdateOrderService updateOrderService) {
        this.updateOrderService = updateOrderService;
    }

    public ResponseEntity<OrderEntity> execute(OrderRequest orderRequest) {
        if (orderRequest != null) {
            return updateOrderService.cancelOrder(orderRequest);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número de pedido inválido");
    }
}
