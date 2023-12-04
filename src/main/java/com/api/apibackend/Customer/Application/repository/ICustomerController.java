package com.api.apibackend.Customer.Application.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Auth.Domain.model.LoginRequest;

public interface ICustomerController {
		
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws Exception;
}
