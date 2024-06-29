package com.api.apibackend.modules.Product.Application.converter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.apibackend.modules.Color.Application.DTOs.ColorDTO;
import com.api.apibackend.modules.Product.Application.DTOs.ProductResponse;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.ProductVariant.Application.DTOs.ProductVariantDTO;

@Component
public class ProductResponseConverter implements Function<ProductEntity, ProductResponse> {

	@Override
	public ProductResponse apply(ProductEntity product) {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setName(product.getName());
			
			if (product.getMidia() != null) {
					productResponse.setUrl(product.getMidia().getUrl());
			}

			List<ProductVariantDTO> productVariantDTOs = product.getProductVariantList()
							.stream()
							.map(variant -> ProductVariantDTO.builder()
											.id(variant.getId())
											.price(variant.getPrice())
											.thumb(variant.getThumb())
											.stock(variant.getStock())
											.color(ColorDTO.builder()
															.name(variant.getColor().getName())
															.hex(variant.getColor().getHex())
															.build())
											.build())
							.collect(Collectors.toList());

			productResponse.setProductVariants(productVariantDTOs);
			return productResponse;
	}

}
