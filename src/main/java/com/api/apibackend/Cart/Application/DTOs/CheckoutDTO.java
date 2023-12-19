package com.api.apibackend.Cart.Application.DTOs;

import lombok.Data;

@Data
public class CheckoutDTO {
    private Long idProduct;
    private int status;
    private int numberOfItemsProductsCart;
}
