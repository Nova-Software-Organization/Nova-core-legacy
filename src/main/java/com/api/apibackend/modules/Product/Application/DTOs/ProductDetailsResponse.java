package com.api.apibackend.modules.Product.Application.DTOs;

import java.util.List;

import com.api.apibackend.modules.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantDetailsDTO;

import lombok.Data;

@Data
public class ProductDetailsResponse {
    private String name;
    private String url;
    private String sku;
    private String longDesc;
    private ProductCategoryDTO category;
    private List<ProductVariantDetailsDTO> productVariantDetails;
}
