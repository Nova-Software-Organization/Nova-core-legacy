package com.api.apibackend.modules.Order.Domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Order.infra.persistence.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderDeleteService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderDeleteService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<?> deleteOrder(Long id) {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);

        if (optionalOrder.isPresent()) {
            orderRepository.delete(optionalOrder.get());
            return ResponseEntity.noContent().build();
        }

        log.error("Pedido não pode ser deletado, pedido não encontrado");
        return ResponseEntity.notFound().build();
    }
}
