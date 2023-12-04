package com.api.apibackend.Order.Domain.model;

import com.api.apibackend.Customer.Application.controller.ClientRequest;
import com.api.apibackend.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.Order.Application.controller.OrderRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequest {

	@JsonProperty("orderRequest")
	private OrderRequest orderRequest;

	@JsonProperty("customerAddress")
	private CustomerAddressRequest customerAddress;
	
	@JsonProperty("clientRequest")
	private ClientRequest clientRequest;

	public CreateOrderRequest() { }

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
