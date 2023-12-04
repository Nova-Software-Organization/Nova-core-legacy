package com.api.apibackend.CustomerAddress.Application.DTO;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

public class CustomerAddress {

	private CustomerEntity customerEntity;

	private AddressEntity addressEntity;

	public CustomerAddress() { }

	public CustomerAddress(CustomerEntity customerEntity, AddressEntity addressEntity) {
		this.customerEntity = customerEntity;
		this.addressEntity = addressEntity;
	}

	public CustomerEntity getcustomerEntity() {
		return customerEntity;
	}

	public void setcustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public AddressEntity getAddressEntity() {
		return addressEntity;
	}

	public void setAddressEntity(AddressEntity addressEntity) {
		this.addressEntity = addressEntity;
	}
}
