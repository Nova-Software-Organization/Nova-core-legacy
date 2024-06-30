/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service;

import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Domain.service.cryptography.AnonymizationService;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

import jakarta.transaction.Transactional;

@Service
public class CustomerOrderService {

    private AnonymizationService anonymizationService;

    @Autowired
    public CustomerOrderService(AnonymizationService anonymizationService) {
        this.anonymizationService = anonymizationService;
    }

    @Transactional
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

        UserEntity userEntity = new UserEntity();
        setHashedPassword(clientRequest.getPassword(), userEntity);
        setHashedEmail(clientRequest.getEmail(), userEntity);

        return customerEntity;
    }

    private void setHashedPassword(String plainPassword, UserEntity userEntity) {
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        userEntity.setPassword(hashedPassword);
    }

    private void setHashedEmail(String email, UserEntity userEntity) {
        String emailAnonymization = anonymizationService.encrypt(email);
        userEntity.setEmail(emailAnonymization);
    }
}
