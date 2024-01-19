/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service.filter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.modules.Customer.Domain.exception.CustomerNotFoundException;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;

public class SearchCustomerByNameService {
    private CustomerRepository customerRepository;

    @Autowired
    public SearchCustomerByNameService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> searchCustomersByName(String name) {
        List<CustomerEntity> customers = customerRepository.findByNameContainingIgnoreCase(name);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("Cliente não encontrado com o nome: " + name);
        }
        return customers;
    }
}
