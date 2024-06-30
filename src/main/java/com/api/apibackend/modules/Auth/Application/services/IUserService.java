package com.api.apibackend.modules.Auth.Application.services;

import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

public interface IUserService {
  UserEntity createUser(UserEntity userEntity, CustomerEntity customerEntity);
  UserEntity saveUser(UserEntity user);
  UserEntity getUser();
}
