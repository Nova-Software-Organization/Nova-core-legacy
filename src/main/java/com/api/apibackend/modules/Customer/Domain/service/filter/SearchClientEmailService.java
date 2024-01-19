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

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.CustomerAddressEntity;

@Service
public class SearchClientEmailService {
    private CustomerRepository customerRepository;

    @Autowired
    public SearchClientEmailService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<CustomerEntity> searchClientByEmail(String email) {
        Optional<CustomerEntity> clientByEmail = customerRepository.findByEmail(email);

        if (clientByEmail.isPresent()) {
            CustomerEntity customerEntity = clientByEmail.get();
            CustomerAddressEntity address = customerEntity.getAddress();

            if (address != null) {
                customerEntity.setAddress(address);
            }
            return Optional.of(customerEntity);
        }

        return Optional.empty();
    }
}
