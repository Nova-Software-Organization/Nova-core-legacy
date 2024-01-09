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
public class ProductCheckQuantity {
    private List<Long> idProduct;
    private int quantityToCheck;
}
