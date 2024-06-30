/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.CustomerAddress.Domain.model;

import lombok.Data;

@Data
public class CustomerAddressRequest {
	private String road;
	private String neighborhood;
	private String housenumber;
	private String state;
	private String cep;
}
