package com.api.apibackend.Customer.Application.DTOs;

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

