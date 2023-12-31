package com.api.apibackend.Customer.Application.useCase;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.ResponseMessageDTO;
import com.api.apibackend.Customer.Domain.service.CustomerService;

@Service
public class CustomerDeleteUseCase {
    private CustomerService customerServiceImp;

    @Autowired
    public CustomerDeleteUseCase(CustomerService customerServiceImp) {
        this.customerServiceImp = customerServiceImp;
    }

    public ResponseEntity<ResponseMessageDTO> execute(Long id) {
        return customerServiceImp.delete(id);
    }
}
