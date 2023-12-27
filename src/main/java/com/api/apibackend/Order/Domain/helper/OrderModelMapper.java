package com.api.apibackend.Order.Domain.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.infra.persistence.entity.OrderEntity;

public class OrderModelMapper {
    private ModelMapper modelMapper;

    @Autowired
    public OrderModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OrderEntity toOrderDTOFromOrderEntity(OrderRequest orderRequest) {
        return modelMapper.map(orderRequest, OrderEntity.class);
    }

    public OrderRequest toOrderEntityFromOrderRequest(OrderEntity requestOrderEntity) {
        return modelMapper.map(requestOrderEntity, OrderRequest.class);
    }
}
