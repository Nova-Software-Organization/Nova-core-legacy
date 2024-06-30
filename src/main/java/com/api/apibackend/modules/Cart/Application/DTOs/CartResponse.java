package com.api.apibackend.modules.Cart.Application.DTOs;

import lombok.Data;

import java.util.List;

import com.api.apibackend.modules.CartItem.Application.DTOs.CartItemDTO;

@Data
public class CartResponse {
    private List<CartItemDTO> cartItems;
    private DiscountDTO discount;
    private Double totalCartPrice;
    private Double totalCargoPrice;
    private Double totalPrice;
}
