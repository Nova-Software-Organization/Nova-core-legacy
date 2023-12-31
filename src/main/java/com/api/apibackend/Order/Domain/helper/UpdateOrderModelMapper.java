package com.api.apibackend.Order.Domain.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class UpdateOrderModelMapper {
    private ModelMapper modelMapper;

    @Autowired
    public UpdateOrderModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderEntity toOrderDTOFromOrderEntity(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, OrderEntity.class);
    }

    public OrderRequest toOrderEntityFromOrderRequest(OrderEntity requestOrderEntity) {
        return modelMapper.map(requestOrderEntity, OrderRequest.class);
    }
}
