/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Customer.Domain.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.repository.CustomerRepository;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.AddressEntity;

@Service
public class CustomerFilterService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerFilterService(CustomerRepository customerRepository) {
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

    public List<CustomerEntity> findCustomersByFiltersMain(String name, String cpf, String lastName) {
        return customerRepository.findByNameContaining(name)
                .stream()
                .filter(customer -> cpf == null || cpf.isEmpty() || customer.getCpf().contains(cpf))
                .filter(customer -> lastName == null || lastName.isEmpty() || customer.getLastName().contains(lastName))
                .collect(Collectors.toList());
    }

    public List<CustomerEntity> findCustomersByFilters(
            String name,
            String cpf,
            String lastName,
            String phone,
            Integer age,
            String gender,
            String email,
            String cep) {

        List<CustomerEntity> filteredCustomers = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByNameContaining(name));
        }

        if (cpf != null && !cpf.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByCpfContaining(cpf));
        }

        if (lastName != null && !lastName.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByLastNameContaining(lastName));
        }

        if (phone != null && !phone.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByPhoneContaining(phone));
        }

        if (age != null) {
            filteredCustomers.addAll(customerRepository.findByAge(age));
        }

        if (gender != null && !gender.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByGenderContaining(gender));
        }

        if (email != null && !email.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByEmailContaining(email));
        }

        if (cep != null && !cep.isEmpty()) {
            filteredCustomers.addAll(customerRepository.findByAddress_CepContaining(cep));
        }

        Set<CustomerEntity> uniqueCustomers = new HashSet<>(filteredCustomers);

        return new ArrayList<>(uniqueCustomers);
    }

    public CustomerEntity getClientById(Long clientId) {
        return customerRepository.findById(clientId).orElse(null);
    }
}
