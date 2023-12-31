package com.api.apibackend.Cart.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import lombok.Data;

@Data
public class CheckoutDTO {
    private List<ProductCheckQuantity> productCheckQuantities;
    private int status;
    private int numberOfItemsProductsCart;
}
