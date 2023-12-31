package com.api.apibackend.Auth.Domain.helpers;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Auth.Application.DTOs.UserDTO;
import com.api.apibackend.Auth.Infra.persistence.entity.UserEntity;


public class UserModelMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UserEntity toUserDTOAsUserEntity(UserDTO request) {
        return this.modelMapper.map(request, UserEntity.class);
    }

    public UserDTO toUserDTOResponse(UserEntity userEntity) {
        return this.modelMapper.map(userEntity, UserDTO.class);
    }
}
