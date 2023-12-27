package com.api.apibackend.Order.Application.DTOs;

import com.api.apibackend.CustomerAddress.Domain.model.CustomerAddressRequest;

import lombok.Data;

@Data
public class OrderUpdateAddressRequest {
    private Long numberOrder;
    private CustomerAddressRequest customerAddressRequest;
}