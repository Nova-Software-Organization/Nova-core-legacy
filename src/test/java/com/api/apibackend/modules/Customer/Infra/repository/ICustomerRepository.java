package com.api.apibackend.modules.Customer.Infra.repository;

import com.api.apibackend.modules.Customer.Application.DTOs.registration.CustomerDTO;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

public interface ICustomerRepository {
    public CustomerEntity savePersist(CustomerDTO customerDTO);
}
