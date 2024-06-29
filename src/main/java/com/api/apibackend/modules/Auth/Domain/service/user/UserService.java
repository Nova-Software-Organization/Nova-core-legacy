/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.service.user;

import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Auth.Domain.repository.IUserService;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Auth.Infra.persistence.repository.UserRepository;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.shared.error.exception.InvalidArgumentException;

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

    @Override
    public UserEntity getUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (Objects.isNull(userName)) {
            return null;
        }

        Optional<UserEntity> user = Optional.ofNullable(userRepository.findByEmail(userName));
        if (user.isEmpty()) {
            return null;
        }
        return user.get();
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        if (Objects.isNull(user)) {
            throw new InvalidArgumentException("Null user");
        }

        return userRepository.save(user);
    }
}