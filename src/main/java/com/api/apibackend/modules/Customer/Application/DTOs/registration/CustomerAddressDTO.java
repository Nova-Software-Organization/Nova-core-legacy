/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Application.DTOs.registration;

import lombok.Data;

@Data
public class CustomerAddressDTO {
	private String road;
	private String neighborhood;
	private String housenumber;
	private String state;
	private String cep;
}
