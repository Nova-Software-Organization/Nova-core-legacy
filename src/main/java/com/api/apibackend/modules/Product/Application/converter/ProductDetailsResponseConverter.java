package com.api.apibackend.modules.Product.Application.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.apibackend.modules.Color.Application.DTOs.ColorDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ProductDetailsResponse;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductCategory.Application.DTOs.ProductCategoryDTO;
import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantDetailsDTO;

@Component
public class ProductDetailsResponseConverter implements Function<ProductEntity, ProductDetailsResponse> {

  @Override
  public ProductDetailsResponse apply(ProductEntity product) {
    ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
    productDetailsResponse.setName(product.getName());
    productDetailsResponse.setUrl(product.getMidia().getUrl());
    productDetailsResponse.setLongDesc(product.getDescription());
    productDetailsResponse.setSku(product.getSku());

    ProductCategoryDTO categoryDTO = ProductCategoryDTO.builder()
        .name(product.getCategory().getName())
        .build();
    productDetailsResponse.setCategory(categoryDTO);

    List<ProductVariantDetailsDTO> productVariantDetailDTOs = product.getProductVariantList()
        .stream()
        .map(productVariant -> ProductVariantDetailsDTO.builder()
            .id(productVariant.getId())
            .width(productVariant.getWidth())
            .height(productVariant.getHeight())
            .composition(productVariant.getComposition())
            .price(productVariant.getPrice())
            .cargoPrice(productVariant.getCargoPrice())
            .taxPercent(productVariant.getTaxPercent())
            .image(productVariant.getImage())
            .thumb(productVariant.getThumb())
            .stock(productVariant.getStock())
            .live(productVariant.getLive())
            .color(ColorDTO.builder()
                .name(productVariant.getColor().getName())
                .hex(productVariant.getColor().getHex())
                .build())
            .build())
        .collect(Collectors.toList());

    productDetailsResponse.setProductVariantDetails(productVariantDetailDTOs);

    return productDetailsResponse;
  }
}