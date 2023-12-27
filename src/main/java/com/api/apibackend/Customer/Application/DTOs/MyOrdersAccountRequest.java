package com.api.apibackend.Customer.Application.DTOs;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyOrdersAccountRequest {
	
	@JsonProperty("client")
	private CustomerEntity client;

	@JsonProperty("address")
	private AddressEntity address;

	public MyOrdersAccountRequest(CustomerEntity client, AddressEntity address) {
		this.client = client;
		this.address = address;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public CustomerEntity getClient() {
		return client;
	}

	public void setClient(CustomerEntity client) {
		this.client = client;
	}
}
