package com.api.apibackend.Auth.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.repository.IUserService;
import com.api.apibackend.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

@Service
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity createUser(UserEntity userEntity, CustomerEntity customerEntity) {
        userEntity.setCustomer(customerEntity);
        UserEntity newUser = userRepository.save(userEntity);
        return newUser;
    }
}