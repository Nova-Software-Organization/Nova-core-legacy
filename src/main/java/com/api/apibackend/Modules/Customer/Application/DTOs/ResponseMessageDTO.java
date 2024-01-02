/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Customer.Application.DTOs;

import java.util.List;

import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;

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
