package com.api.apibackend.OrderItem.Application.DTOs;

import java.math.BigDecimal;

import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Product.Infra.entity.ProductEntity;

import lombok.Data;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

@Data
public class OrderItemDTO {
    private Long id;
    private OrderEntity order;
    private ProductEntity product;
    private int quantity;
    private BigDecimal unitPrice;
}
