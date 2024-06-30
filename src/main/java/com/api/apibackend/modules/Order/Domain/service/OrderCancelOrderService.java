package com.api.apibackend.modules.Order.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderCancelOrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderCancelOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<OrderEntity> cancelOrder(OrderRequest numberOrder) {
        Optional<OrderEntity> order = orderRepository.findById(numberOrder.getNumberOrder());

        if (order.isPresent()) {
            OrderEntity updateOrder = order.get();
            updateOrder.setStatus("cancelado");
            log.error("Status do pedido foi alterado com sucesso");
            return ResponseEntity.ok(orderRepository.save(updateOrder));
        }

        log.error("Atualização não pode ser concluida, pedido não encontrado");
        return ResponseEntity.notFound().build();
    }
}
