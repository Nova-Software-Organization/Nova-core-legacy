package com.api.apibackend.Auth.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Infra.entity.UserEntity;
import com.api.apibackend.Auth.Infra.repository.UserRepository;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class UserDetailsService {
    
    private UserRepository userRepository;

    @Autowired
    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity, CustomerEntity customerEntity) {
        userEntity.setCustomer(customerEntity);
        UserEntity newUser = userRepository.save(userEntity);
        return newUser;
    }
}