package com.api.apibackend.Product.Domain.model;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Data;

@Data
public class SupplierAddress {
    private String road;
    private String neighborhood;
    private String numberHouseOrCompany;
    private String cep;
}
