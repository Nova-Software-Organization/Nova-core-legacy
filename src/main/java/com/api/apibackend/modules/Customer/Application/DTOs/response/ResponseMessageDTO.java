/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.DTOs.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

import lombok.Data;

@Data
public class ResponseMessageDTO {
    private String message;
    private String className;
    private String errorMessage;
    private List<CustomerEntity> customer;
    private Page<CustomerEntity> consultPageCustomer;

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
    
    public ResponseMessageDTO(String message, String className, String errorMessage, List<CustomerEntity> customer, Page<CustomerEntity> consultPageCustomer) {
        this.message = message;
        this.className = className;
        this.errorMessage = errorMessage;
        this.customer = customer;
        this.consultPageCustomer = consultPageCustomer;
    }
}
