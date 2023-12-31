package com.api.apibackend.Cart.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

public class UnavailableProductsResponseDTO {
    private List<Long> unavailableProducts;
    private ResponseMessageDTO responseMessage;

    public UnavailableProductsResponseDTO(List<Long> unavailableProducts, ResponseMessageDTO responseMessage) {
        this.unavailableProducts = unavailableProducts;
        this.responseMessage = responseMessage;
    }

    public List<Long> getUnavailableProducts() {
        return unavailableProducts;
    }

    public ResponseMessageDTO getResponseMessage() {
        return responseMessage;
    }
}
