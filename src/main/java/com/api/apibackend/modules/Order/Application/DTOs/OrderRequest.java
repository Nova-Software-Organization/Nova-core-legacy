package com.api.apibackend.modules.Order.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.List;

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.modules.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.modules.Product.Infra.entity.ProductEntity;
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
