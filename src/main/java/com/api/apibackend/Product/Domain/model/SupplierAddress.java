package com.api.apibackend.Product.Domain.model;

import lombok.Data;

@Data
public class SupplierAddress {
    private String road;
    private String neighborhood;
    private String numberHouseOrCompany;
    private String cep;
}
