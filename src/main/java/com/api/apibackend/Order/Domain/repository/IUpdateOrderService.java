package com.api.apibackend.Order.Domain.repository;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.Order.infra.entity.OrderEntity;

public interface IUpdateOrderService {
    public ResponseEntity<OrderEntity> updateAddressOrder(OrderUpdateAddressRequest numberOrder);
    public ResponseEntity<OrderEntity> updateOrder(Long id, OrderRequest orderRequest);
    public ResponseEntity<OrderEntity> cancelOrder(OrderRequest numberOrder);
    public ResponseEntity<?> deleteOrder(Long id);
}
