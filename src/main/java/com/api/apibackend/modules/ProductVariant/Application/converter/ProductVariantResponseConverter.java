package com.api.apibackend.modules.ProductVariant.Application.converter;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.api.apibackend.modules.Color.Application.DTOs.ColorDTO;
import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantDTO;
import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantResponse;
import com.api.apibackend.modules.ProductVariant.infra.persistence.entity.ProductVariantEntity;

@Component
public class ProductVariantResponseConverter implements Function<ProductVariantEntity, ProductVariantResponse> {
    
    @Override
    public ProductVariantResponse apply(ProductVariantEntity productVariant) {
        ProductVariantResponse productVariantResponse = new ProductVariantResponse();
        productVariantResponse.setId(productVariant.getId());
        productVariantResponse.setName(productVariant.getProduct().getName());
        productVariantResponse.setUrl(productVariant.getProduct().getMidia().getUrl());
        productVariantResponse.setProductVariant(ProductVariantDTO
                .builder()
                .id(productVariant.getId())
                .price(productVariant.getPrice())
                .thumb(productVariant.getThumb())
                .stock(productVariant.getStock())
                .color(ColorDTO
                        .builder()
                        .name(productVariant.getColor().getName())
                        .hex(productVariant.getColor().getHex())
                        .build())
                .build()
        );

        return productVariantResponse;
    }
}