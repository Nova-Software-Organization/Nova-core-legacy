package com.api.apibackend.OrderItem.Domain.helper;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.OrderItem.Application.DTOs.OrderItemDTO;
import com.api.apibackend.OrderItem.infra.persistence.entity.OrderItemEntity;


public class OrderItemModelMapper {
    private ModelMapper modelMapper;

    @Autowired
    public OrderItemModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderItemEntity toOrderItemDTOFromOrderItemEntity(OrderItemEntity orderItemRequest) {
        return modelMapper.map(orderItemRequest, OrderItemEntity.class);
    }

    public OrderItemDTO toOrderEntityFromOrderRequest(OrderItemEntity requestOrderItemEntity) {
        return modelMapper.map(requestOrderItemEntity, OrderItemDTO.class);
    }
}
