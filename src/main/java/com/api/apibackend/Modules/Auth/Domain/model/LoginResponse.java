/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Auth.Domain.model;


import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Modules.CustomerAddress.infra.entity.AddressEntity;

public class LoginResponse {
	private CustomerEntity client;
	private AddressEntity address;

	public LoginResponse() { }

	public LoginResponse(CustomerEntity client, AddressEntity address) {
		this.client = client;
		this.address = address;
	}

	public CustomerEntity getClient() {
		return client;
	}

	public void setClient(CustomerEntity client) {
		this.client = client;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}
}

