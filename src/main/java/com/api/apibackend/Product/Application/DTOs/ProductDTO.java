package com.api.apibackend.Product.Application.DTOs;

import com.api.apibackend.Midia.infra.persistence.entity.MidiaEntity;
import com.api.apibackend.Price.infra.entity.PriceEntity;
import com.api.apibackend.ProductCategory.infra.persistence.entity.ProductCategoryEntity;
import com.api.apibackend.Supplier.Infra.entity.SupplierEntity;

import lombok.Data;

@Data
public class ProductDTO {
    private Long idProduct;
    private MidiaEntity midia;
    private ProductCategoryEntity category;
    private String name;
    private SupplierEntity supplierEntity;
    private PriceEntity priceEntity;
    private int quantityInStock;
    private String description;
    private int status;
    private String sku;
}
