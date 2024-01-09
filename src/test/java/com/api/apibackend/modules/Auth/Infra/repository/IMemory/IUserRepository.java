/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Infra.repository.IMemory;

import com.api.apibackend.modules.Auth.Application.DTOs.UserDTO;
import com.api.apibackend.modules.Auth.Infra.persistence.entity.UserEntity;

public interface IUserRepository {
    UserEntity savePersist(UserDTO userDTO);
}
