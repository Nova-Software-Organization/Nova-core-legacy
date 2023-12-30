package com.api.apibackend.Product.Application.DTOs;

import com.api.apibackend.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.Stock.Application.DTOs.StockDTO;
import com.api.apibackend.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Unity.Application.DTOs.UnityDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductDTO {
    private Long idProduct;
    private String name;    
    private String description;
    private int status;
    private String sku;

    @JsonProperty("midia")
    private MidiaDTO midia;

    @JsonProperty("category")
    private ProductCategoryDTO category;

    @JsonProperty("supplier")
    private SupplierDTO supplierEntity;

    @JsonProperty("price")
    private PriceDTO priceEntity;

    @JsonProperty("stock")
    private StockDTO stockEntity;

    @JsonProperty("unity")
    private UnityDTO unityEntity;
}
