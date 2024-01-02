package com.api.apibackend.Modules.Product.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import com.api.apibackend.Modules.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.Modules.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.Modules.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.Modules.Stock.Application.DTOs.StockDTO;
import com.api.apibackend.Modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.Modules.Unity.Application.DTOs.UnityDTO;
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
