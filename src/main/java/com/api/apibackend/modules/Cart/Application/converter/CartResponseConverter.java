package com.api.apibackend.modules.Cart.Application.converter;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.apibackend.modules.Cart.Application.DTOs.CartResponse;
import com.api.apibackend.modules.Cart.Application.DTOs.DiscountDTO;
import com.api.apibackend.modules.Cart.Infra.persistence.entity.CartEntity;
import com.api.apibackend.modules.CartItem.Application.DTOs.CartItemDTO;
import com.api.apibackend.modules.Color.Application.DTOs.ColorDTO;

@Component
public class CartResponseConverter implements Function<CartEntity, CartResponse> {

     @Override
        public CartResponse apply(CartEntity cart) {
                CartResponse cartResponse = new CartResponse();

                List<CartItemDTO> cartItems = cart.getCartItems()
                                .stream()
                                .map(cartItem -> CartItemDTO.builder()
                                                .id(cartItem.getId())
                                                .url(cartItem.getProductVariant().getProduct().getUrl())
                                                .name(cartItem.getProductVariant().getProduct().getName())
                                                .price(cartItem.getProductVariant().getPrice())
                                                .amount(cartItem.getAmount())
                                                .thumb(cartItem.getProductVariant().getThumb())
                                                .stock(cartItem.getProductVariant().getStock())
                                                .color(ColorDTO.builder()
                                                                .name(cartItem.getProductVariant().getColor().getName())
                                                                .hex(cartItem.getProductVariant().getColor().getHex())
                                                                .build())
                                                .build())
                                .collect(Collectors.toList());

                cartResponse.setCartItems(cartItems);

                if (Objects.nonNull(cart.getDiscount())) {
                        cartResponse.setDiscount(DiscountDTO.builder()
                                        .discountPercent(cart.getDiscount().getRoundedDiscountValue())
                                        .status(cart.getDiscount().getStatus())
                                        .build());
                }

                cartResponse.setTotalCartPrice(cart.getTotalCartPrice());
                cartResponse.setTotalCargoPrice(cart.getTotalCargoPrice());
                cartResponse.setTotalPrice(cart.getTotalPrice());
                return cartResponse;
        }
}
