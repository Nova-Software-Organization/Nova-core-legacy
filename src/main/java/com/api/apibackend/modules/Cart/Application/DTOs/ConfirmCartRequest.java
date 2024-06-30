package com.api.apibackend.modules.Cart.Application.DTOs;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.api.apibackend.modules.CartItem.Application.DTOs.CartItemDTO;

import java.util.List;

@Data
public class ConfirmCartRequest {
    @NotNull
    private List<CartItemDTO> cartItems;
    private DiscountDTO discount;

    @Min(0)
    private Float totalCartPrice;

    @Min(0)
    private Float totalCargoPrice;

    @Min(0)
    private Float totalPrice;


}
