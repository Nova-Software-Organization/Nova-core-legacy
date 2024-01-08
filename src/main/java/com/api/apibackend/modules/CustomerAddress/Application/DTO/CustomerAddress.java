/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.CustomerAddress.Application.DTO;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;

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
