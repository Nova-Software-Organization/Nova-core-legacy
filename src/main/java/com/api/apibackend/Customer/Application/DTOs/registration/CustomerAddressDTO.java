package com.api.apibackend.Customer.Application.DTOs.registration;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class CustomerAddressDTO {
	private String road;
	private String neighborhood;
	private String housenumber;
	private String state;
	private String cep;
}
