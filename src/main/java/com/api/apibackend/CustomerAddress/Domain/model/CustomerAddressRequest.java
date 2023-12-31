package com.api.apibackend.CustomerAddress.Domain.model;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class CustomerAddressRequest {
	private String road;
	private String neighborhood;
	private String housenumber;
	private String state;
	private String cep;
}
