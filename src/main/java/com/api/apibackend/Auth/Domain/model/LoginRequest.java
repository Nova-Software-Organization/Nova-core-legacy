package com.api.apibackend.Auth.Domain.model;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginRequest {
	@JsonProperty("password")
	private String password;

	@JsonProperty("username")
	private String username;
}
