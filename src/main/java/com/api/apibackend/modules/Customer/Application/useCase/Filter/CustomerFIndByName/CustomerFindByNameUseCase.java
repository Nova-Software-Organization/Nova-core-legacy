/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerFIndByName;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class CustomerFindByNameUseCase {
    private CustomerFilterService customerFilterService;

    @Autowired
    public CustomerFindByNameUseCase(CustomerFilterService customerFilterService) {
        this.customerFilterService = customerFilterService;
    }

     public List<CustomerEntity> execute(String name) {
        return customerFilterService.searchCustomersByName(name);
    }
}