package com.api.apibackend.Order.Domain.repository;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.infra.entity.OrderEntity;

public interface IUpdateOrderService {

    ResponseEntity<OrderEntity> updateOrder(Long id, OrderRequest orderRequest);

    ResponseEntity<?> deleteOrder(Long id);
}
