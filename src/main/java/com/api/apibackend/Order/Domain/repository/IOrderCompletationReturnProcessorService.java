package com.api.apibackend.Order.Domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Order.infra.entity.OrderEntity;

public interface IOrderCompletationReturnProcessorService {
    public ResponseEntity<List<OrderEntity>> getOrderList();

    public ResponseEntity<Optional<OrderEntity>> getOrderId(Long idOrder);
}
