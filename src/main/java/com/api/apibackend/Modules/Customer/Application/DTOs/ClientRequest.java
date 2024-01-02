/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Application.DTOs;

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
