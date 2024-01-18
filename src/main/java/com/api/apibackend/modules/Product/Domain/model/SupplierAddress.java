/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Domain.model;

import lombok.Data;

@Data
public class SupplierAddress {
    private String road;
    private String neighborhood;
    private String numberHouseOrCompany;
    private String cep;
}
