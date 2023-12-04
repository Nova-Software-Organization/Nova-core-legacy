package com.api.apibackend.CustomerAddress.Domain.model;

import lombok.Data;

@Data
public class CustomerAddressRequest {
	
	private String road;
	
	private String neighborhood;
	
	private String housenumber;
	
	private String state;
	
	private String cep;
}
