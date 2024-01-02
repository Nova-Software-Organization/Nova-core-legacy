/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.OrderItem.Application.DTOs;

import java.math.BigDecimal;

import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Modules.Product.Infra.entity.ProductEntity;

import lombok.Data;


@Data
public class OrderItemDTO {
    private Long id;
    private OrderEntity order;
    private ProductEntity product;
    private int quantity;
    private BigDecimal unitPrice;
}
