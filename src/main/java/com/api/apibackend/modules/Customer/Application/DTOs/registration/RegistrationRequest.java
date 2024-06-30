/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.DTOs.registration;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder("Registration")
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

