package com.api.apibackend.Customer.Application.DTOs;

import lombok.Data;

@Data
public class CustomerAddressDTO {
	
	private String road;
	
	private String neighborhood;
	
	private String housenumber;
	
	private String state;
	
	private String cep;
}
