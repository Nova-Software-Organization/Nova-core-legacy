package com.api.apibackend.SupplierAddress.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class SupplierAddressDTO {
    private Long idSupplierAddress;
    private String road;
    private String neighborhood;
    private String numberHouseOrCompany;
    private String cep;
}
