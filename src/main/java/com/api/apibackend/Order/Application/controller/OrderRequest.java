package com.api.apibackend.Order.Application.controller;

import java.util.ArrayList;
import java.util.List;

import com.api.apibackend.Customer.Application.controller.ClientRequest;
import com.api.apibackend.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.Product.infra.entity.ProductEntity;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder("orderRequest")
public class OrderRequest {
	
	private Long numberOrder;
	
	private List<OrderItem> items;
	
	private String customerName;
	
	private CustomerAddressRequest customerAddress;
	
	private ClientRequest clientRequest;
	
	private String customerEmail;
	
	private String paymentMethod;
	
	public List<ProductEntity> getProducts() {
		List<ProductEntity> products = new ArrayList<>();

		for (OrderItem orderItem : items) {
			products.add(orderItem.getProduct());
		}

		return products;
	}
}
