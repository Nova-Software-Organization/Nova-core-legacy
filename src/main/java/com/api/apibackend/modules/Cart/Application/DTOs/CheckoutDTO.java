/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Cart.Application.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class CheckoutDTO {
    private List<ProductCheckQuantity> productCheckQuantities;
    private int status;
    private int numberOfItemsProductsCart;
}
