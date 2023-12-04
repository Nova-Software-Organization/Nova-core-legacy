package com.api.apibackend.Auth.Domain.model;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequest {
	
	@JsonProperty("email")
	private String email;

	@JsonProperty("password")
	private String password;
	 
	public LoginRequest(CustomerEntity customerEntity) {
		this.email = customerEntity.getEmail();
		this.password = customerEntity.getPassword();
	}
}
