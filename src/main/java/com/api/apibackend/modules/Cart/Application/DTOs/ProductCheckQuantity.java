package com.api.apibackend.modules.Cart.Application.DTOs;

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
public class ProductCheckQuantity {
    private List<Long> idProduct;
    private int quantityToCheck;
}
