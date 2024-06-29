package com.api.apibackend.modules.Cart.Application.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DiscountDTO {
    private Integer discountPercent;
    private Integer status;
}
