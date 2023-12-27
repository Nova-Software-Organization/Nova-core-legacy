package com.api.apibackend.Customer.Domain.service;

import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.api.apibackend.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class CustomerOrderService {

    public CustomerEntity createNewCustomerOrder(ClientRequest clientRequest) {

        Objects.requireNonNull(clientRequest, "Objeto de cliente não pode ser iniciado com null");

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(clientRequest.getName());
        customerEntity.setEmail(clientRequest.getEmail());
        customerEntity.setAge(clientRequest.getAge());
        customerEntity.setCpf(clientRequest.getCpf());
        customerEntity.setGender(clientRequest.getGender());
        customerEntity.setPhone(clientRequest.getPhone());
        customerEntity.setLastName(clientRequest.getLastName());

        setHashedPassword(clientRequest.getPassword(), customerEntity);

        return customerEntity;
    }

    private void setHashedPassword(String plainPassword, CustomerEntity customerEntity) {
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        customerEntity.setPassword(hashedPassword);
    }
}
