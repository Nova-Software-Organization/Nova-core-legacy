/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Order.Domain.service.filters.OrderFindById;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;

@Service
public class OrderFindById {
    private OrderRepository orderRepository;

    @Autowired
    public OrderFindById(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<Optional<OrderEntity>> getOrderId(Long idOrder) {
        Optional<OrderEntity> orderSearchId = orderRepository.findById(idOrder);
        return ResponseEntity.ok(orderSearchId);
    }
}
