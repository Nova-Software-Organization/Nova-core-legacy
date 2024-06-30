package com.api.apibackend.modules.Order.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.shared.util.InputValidator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOrderRequest {
	@JsonProperty("orderRequest")
	private OrderRequest orderRequest;

	@JsonProperty("customerAddress")
	private CustomerAddressRequest customerAddress;

	@JsonProperty("clientRequest")
	private ClientRequest clientRequest;

	@JsonIgnore
	private InputValidator inputValidator;

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

	/**
     * Verifica se a instância de CreateOrderRequest é válido para criação do pedido.
     * @return true se for válida, false caso contrário.
     */
    public boolean isValid() {
        return inputValidator.isValidInput(this);
    }
}
