package com.api.apibackend.modules.Cart.Application.services;

import com.api.apibackend.modules.Cart.Application.DTOs.CartResponse;
import com.api.apibackend.modules.Cart.Application.DTOs.ConfirmCartRequest;
import com.api.apibackend.modules.Cart.Infra.persistence.entity.CartEntity;

public interface CartService {
    CartResponse addToCart(Long id, Integer amount);
    CartResponse incrementCartItem(Long cartItemId, Integer amount);
    CartResponse decrementCartItem(Long cartItemId, Integer amount);
    CartResponse fetchCart();
    CartResponse removeFromCart(Long id);
    boolean confirmCart(ConfirmCartRequest confirmCartRequest);
    CartEntity getCart();
    void saveCart(CartEntity cart);
    void emptyCart();
    CartEntity calculatePrice(CartEntity cart);
}