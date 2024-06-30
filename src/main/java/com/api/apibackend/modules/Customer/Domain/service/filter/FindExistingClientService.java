/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service.filter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;

@Service
public class FindExistingClientService {
    private CustomerRepository customerRepository;

    @Autowired
    public FindExistingClientService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerEntity findExistingClient(ClientRequest clientRequest) {
        if (clientRequest != null) {
            String email = clientRequest.getEmail();
            if (email != null) {
                Optional<CustomerEntity> existingClient = Optional.ofNullable(customerRepository.findByEmail(email));
                return existingClient.orElse(null);
            }
        }
        return null;
    }
}
