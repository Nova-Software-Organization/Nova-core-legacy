/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerOrder;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

public class CustomerGetSortedByNameUseCase {
    private  CustomerFilterService customerFilterService;

    @Autowired
    public CustomerGetSortedByNameUseCase(CustomerFilterService customerFilterService) {
        this.customerFilterService = customerFilterService;
    }

    public List<CustomerEntity> execute(String sort) {
        return customerFilterService.getCustomersSortedByName(sort);
    }
}
