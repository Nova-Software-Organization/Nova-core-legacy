/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Supplier.Application.DTOs;

import java.util.Date;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long idSupplier;
    private String nameCompany;
    private String region;
    private String officeSupplier;
    private Date dateCreated;
    private String contact;
    private String cnpj;
    private int status;
    private SupplierAddressDTO supplierAddress;
}
