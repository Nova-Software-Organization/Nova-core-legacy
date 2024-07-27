/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.DTOs;

import com.api.apibackend.modules.Midia.Application.DTOs.MidiaDTO;
import com.api.apibackend.modules.Price.Application.DTOs.PriceDTO;
import com.api.apibackend.modules.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.modules.Stock.Application.DTOs.StockDTO;
import com.api.apibackend.modules.Supplier.Application.DTOs.SupplierDTO;
import com.api.apibackend.modules.Unity.Application.DTOs.UnityDTO;
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
