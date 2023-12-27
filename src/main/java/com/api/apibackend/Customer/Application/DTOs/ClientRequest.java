package com.api.apibackend.Customer.Application.DTOs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder("clientRequest")
public class ClientRequest {
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private String password;
	private String gender;
	private String lastName;
	private int age;
}
