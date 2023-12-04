package com.api.apibackend.Order.Domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Order.infra.entity.OrderEntity;
import com.api.apibackend.Order.infra.repository.OrderRepository;

@Service
public class OrderCompletionReturnProcessor {
    
    private OrderRepository orderRepository;

    public ResponseEntity<List<OrderEntity>> getOrderList() {
        List<OrderEntity> listOrder = orderRepository.findAll();
        return ResponseEntity.ok(listOrder);
    }

    public ResponseEntity<Optional<OrderEntity>> getOrderId(Long idOrder) {
        Optional<OrderEntity> orderSearchId = orderRepository.findById(idOrder);
        return ResponseEntity.ok(orderSearchId);
    }
}
