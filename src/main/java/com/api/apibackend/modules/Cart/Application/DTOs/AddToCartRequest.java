package com.api.apibackend.modules.Cart.Application.DTOs;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddToCartRequest {
    @Min(1)
    @NotNull
    private Long productVariantId;

    @Min(1)
    @NotNull
    private Integer amount;
}
