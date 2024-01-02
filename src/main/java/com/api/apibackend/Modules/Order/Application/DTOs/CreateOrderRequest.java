package com.api.apibackend.Modules.Order.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import com.api.apibackend.Modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.Modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequest {
	@JsonProperty("orderRequest")
	private OrderRequest orderRequest;

	@JsonProperty("customerAddress")
	private CustomerAddressRequest customerAddress;

	@JsonProperty("clientRequest")
	private ClientRequest clientRequest;

	public CreateOrderRequest() {
	}

	public OrderRequest getOrderRequest() {
		return orderRequest;
	}

	public void setOrderRequest(OrderRequest orderRequest) {
		this.orderRequest = orderRequest;
	}

	public CustomerAddressRequest getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(CustomerAddressRequest customerAddress) {
		this.customerAddress = customerAddress;
	}

	public ClientRequest getClientRequest() {
		return clientRequest;
	}

	public void setClientRequest(ClientRequest clientRequest) {
		this.clientRequest = clientRequest;
	}
}
