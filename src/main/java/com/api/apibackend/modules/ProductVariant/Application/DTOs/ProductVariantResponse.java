package com.api.apibackend.modules.ProductVariant.Application.DTOs;

import lombok.Data;

@Data
public class ProductVariantResponse {
    private Long id;
    private String name;
    private String url;
    private ProductVariantDTO productVariant;
}
