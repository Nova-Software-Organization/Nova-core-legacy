/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;

@Service
public class FIndCustomerByFIltersMainService {
    private CustomerRepository customerRepository;

    @Autowired
    public FIndCustomerByFIltersMainService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerEntity> findCustomersByFiltersMain(String name, String cpf, String lastName) {
        return customerRepository.findByNameContaining(name)
                .stream()
                .filter(customer -> cpf == null || cpf.isEmpty() || customer.getCpf().contains(cpf))
                .filter(customer -> lastName == null || lastName.isEmpty() || customer.getLastName().contains(lastName))
                .collect(Collectors.toList());
    }
}
