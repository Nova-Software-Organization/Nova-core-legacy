package com.api.apibackend.Customer.Domain.service;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

@Service
public class CustomerSearchService {

    private CustomerRepository customerRepository;
    
    @Autowired
    public CustomerSearchService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<CustomerEntity> searchClientByEmail(String email) {
        Optional<CustomerEntity> clientByEmail = customerRepository.findByEmail(email);
    
        if (clientByEmail.isPresent()) {
            CustomerEntity customerEntity = clientByEmail.get();
            AddressEntity address = customerEntity.getAddress();
    
            if (address != null) {
                customerEntity.setAddress(address);
            }
            return Optional.of(customerEntity);
        }
    
        return Optional.empty();
    }

    public CustomerEntity findExistingClient(ClientRequest clientRequest) {
        if (clientRequest != null) {
            String email = clientRequest.getEmail();
            if (email != null) {
                Optional<CustomerEntity> existingClient = customerRepository.findByEmail(email);
                return existingClient.orElse(null);
            }
        }
        return null;
    }

    public CustomerEntity getClientById(Long clientId) {
        return customerRepository.findById(clientId).orElse(null);
    }
}
