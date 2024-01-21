/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;

@Service
public class GetCustomerPaginationService {
    private CustomerRepository customerRepository;

    @Autowired
    public GetCustomerPaginationService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    public Page<CustomerEntity> getCustomersWithPagination(PageRequest pageRequest) {
        return customerRepository.findAll(pageRequest);
    }
}
