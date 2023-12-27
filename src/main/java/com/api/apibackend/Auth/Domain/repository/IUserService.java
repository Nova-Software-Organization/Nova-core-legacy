package com.api.apibackend.Auth.Domain.repository;

import com.api.apibackend.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

public interface IUserService {
    UserEntity createUser(UserEntity userEntity, CustomerEntity customerEntity);
}
