/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Product.Application.DTOs;

import java.util.List;

import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

import lombok.Data;

@Data
public class ResponseMessageDTO {
    private String message;
    private String className;
    private String errorMessage;
    private List<ProductEntity> product;

    public ResponseMessageDTO(String message, String className, String errorMessage) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
    }

    public ResponseMessageDTO(String message, String className, List<ProductEntity> product, String errorMessage) {
        this.message = message;
        this.className = className;
        this.product = product;
        this.errorMessage = errorMessage;
    }
}