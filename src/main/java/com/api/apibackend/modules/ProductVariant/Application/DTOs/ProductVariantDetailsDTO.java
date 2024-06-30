package com.api.apibackend.modules.ProductVariant.Application.DTOs;

import com.api.apibackend.modules.Color.Application.DTOs.ColorDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductVariantDetailsDTO {
   private Long id;
    private String width;
    private String height;
    private String composition;
    private Float price;
    private Float cargoPrice;
    private Float taxPercent;
    private String image;
    private String thumb;
    private Integer stock;
    private Integer live;
    private ColorDTO color;
}
