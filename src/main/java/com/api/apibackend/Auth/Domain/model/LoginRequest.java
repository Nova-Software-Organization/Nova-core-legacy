package com.api.apibackend.Auth.Domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequest {
	@JsonProperty("password")
	private String password;

	@JsonProperty("username")
	private String username;
}
