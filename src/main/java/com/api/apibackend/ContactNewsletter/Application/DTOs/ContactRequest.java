package com.api.apibackend.ContactNewsletter.Application.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ContactRequest {

	@JsonProperty("name")
	public String name;

	@JsonProperty("email")
	public String email;

	@JsonProperty("phone")
	public String phone;
}
