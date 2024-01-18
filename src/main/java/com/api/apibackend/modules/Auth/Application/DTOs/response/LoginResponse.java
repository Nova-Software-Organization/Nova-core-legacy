/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Application.DTOs.response;


import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.CustomerAddressEntity;

public class LoginResponse {
	private CustomerEntity client;
	private CustomerAddressEntity address;

	public LoginResponse() { }

	public LoginResponse(CustomerEntity client, CustomerAddressEntity address) {
		this.client = client;
		this.address = address;
	}

	public CustomerEntity getClient() {
		return client;
	}

	public void setClient(CustomerEntity client) {
		this.client = client;
	}

	public CustomerAddressEntity getAddress() {
		return address;
	}

	public void setAddress(CustomerAddressEntity address) {
		this.address = address;
	}
}

