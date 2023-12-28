package com.api.apibackend.Order.Application.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Order.Application.DTOs.CreateOrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.Order.Application.DTOs.OrderUpdateAddressRequest;

public interface IOrderController {
	ResponseEntity<?> listOrders();
	ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest);
	ResponseEntity<?> updateOrderAddress(@RequestBody OrderUpdateAddressRequest orderRequest);
	ResponseEntity<?> canceladOrder(@RequestBody OrderRequest orderRequest);
}
