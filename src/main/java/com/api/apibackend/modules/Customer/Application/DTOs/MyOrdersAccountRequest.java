/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.DTOs;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.CustomerAddressEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyOrdersAccountRequest {
	
	@JsonProperty("customer")
	private CustomerEntity client;

	@JsonProperty("customerAddress")
	private CustomerAddressEntity address;

	public MyOrdersAccountRequest(CustomerEntity client, CustomerAddressEntity address) {
		this.client = client;
		this.address = address;
	}

	public CustomerAddressEntity getAddress() {
		return address;
	}

	public void setAddress(CustomerAddressEntity address) {
		this.address = address;
	}

	public CustomerEntity getClient() {
		return client;
	}

	public void setClient(CustomerEntity client) {
		this.client = client;
	}
}
