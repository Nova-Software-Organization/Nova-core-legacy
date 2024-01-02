package com.api.apibackend.Modules.Order.Domain.repository;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.http.ResponseEntity;

import com.api.apibackend.Modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Modules.Order.Application.DTOs.OrderUpdateAddressRequest;
import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Modules.OrderAddress.Infra.persistence.entity.OrderAddressEntity;

public interface IUpdateOrderService {
    public ResponseEntity<OrderAddressEntity> updateAddressOrder(OrderUpdateAddressRequest numberOrder);
    public ResponseEntity<OrderEntity> updateOrder(Long id, OrderRequest orderRequest);
    public ResponseEntity<OrderEntity> cancelOrder(OrderRequest numberOrder);
    public ResponseEntity<?> deleteOrder(Long id);
}
