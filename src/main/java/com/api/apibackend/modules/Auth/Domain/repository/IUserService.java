/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.repository;

import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;
import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;

public interface IUserService {
    UserEntity createUser(UserEntity userEntity, CustomerEntity customerEntity);
    UserEntity getUser();
    UserEntity saveUser(UserEntity user);
}
