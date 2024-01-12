/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Domain.repository.IUserService;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

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