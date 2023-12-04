package com.api.apibackend.Customer.Domain.repository;

import org.springframework.stereotype.Repository;

import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.CustomerAddress.infra.entity.AddressEntity;

@Repository
public interface IClientService {
    
    CustomerEntity createClient(CustomerEntity customerEntity, AddressEntity addressEntity);

    void updateClient(Long clientId, CustomerEntity updatedClient, AddressEntity updatedAddress);

    void deleteClient(Long clientId);
}
