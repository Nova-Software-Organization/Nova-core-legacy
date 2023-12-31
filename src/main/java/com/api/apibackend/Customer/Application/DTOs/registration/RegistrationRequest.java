package com.api.apibackend.Customer.Application.DTOs.registration;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

public class RegistrationRequest {
	private CustomerDTO customerDTO;
	private CustomerAddressDTO customerAddressDTO;

	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}

	public void setcustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}

	public CustomerAddressDTO getCustomerAddressDTO() {
		return customerAddressDTO;
	}

	public void setCustomerAddressDTO(CustomerAddressDTO customerAddressDTO) {
		this.customerAddressDTO = customerAddressDTO;
	}
}

