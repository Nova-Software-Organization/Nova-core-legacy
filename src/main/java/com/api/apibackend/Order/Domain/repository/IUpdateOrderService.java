package com.api.apibackend.Order.Domain.repository;

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

public interface IUpdateOrderService {
    public ResponseEntity<OrderAddressEntity> updateAddressOrder(OrderUpdateAddressRequest numberOrder);
    public ResponseEntity<OrderEntity> updateOrder(Long id, OrderRequest orderRequest);
    public ResponseEntity<OrderEntity> cancelOrder(OrderRequest numberOrder);
    public ResponseEntity<?> deleteOrder(Long id);
}
