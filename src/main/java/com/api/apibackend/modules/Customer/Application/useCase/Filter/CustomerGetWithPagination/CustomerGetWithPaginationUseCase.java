/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Application.useCase.Filter.CustomerGetWithPagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Domain.service.CustomerFilterService;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class CustomerGetWithPaginationUseCase {
    private CustomerFilterService customerFilterService;

    @Autowired
    public CustomerGetWithPaginationUseCase(CustomerFilterService customerFilterService) {
        this.customerFilterService = customerFilterService;
    }

    public Page<CustomerEntity> getCustomersWithPagination(int page, int size) {
        return customerFilterService.getCustomersWithPagination(PageRequest.of(page, size));
    }
}
