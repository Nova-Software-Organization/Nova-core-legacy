package com.api.apibackend.modules.Product.Application.DTOs;

import lombok.Data;

import java.util.List;

import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantDTO;

@Data
public class ProductResponse {
    private String name;
    private String url;
    private List<ProductVariantDTO> productVariants;
}