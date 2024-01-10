/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Application.useCase.CustomerDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Application.DTOs.response.ResponseMessageDTO;
import com.api.apibackend.modules.Customer.Domain.service.CustomerService;

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
