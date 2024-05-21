package com.api.apibackend.modules.ProductVariant.Application.DTOs;

import com.api.apibackend.modules.Color.Application.DTOs.ColorDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductVariantDTO {
    private Long id;
    private Float price;
    private String thumb;
    private Integer stock;
    private ColorDTO color;
}
