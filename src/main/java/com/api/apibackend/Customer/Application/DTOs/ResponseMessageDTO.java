package com.api.apibackend.Customer.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.List;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

import lombok.Data;

@Data
public class ResponseMessageDTO {
    private String message;
    private String className;
    private String errorMessage;
    private List<CustomerEntity> customer;

    public ResponseMessageDTO(String message, String className, List<CustomerEntity> customer, String errorMessage) {
        this.message = message;
        this.className = className;
        this.customer = customer;
        this.errorMessage = errorMessage;
    }

    public ResponseMessageDTO(String message, String className, String errorMessage) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
    }
}
