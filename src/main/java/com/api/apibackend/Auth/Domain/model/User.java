package com.api.apibackend.Auth.Domain.model;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Auth.Infra.persistence.entity.UserEntity;

public class User {
    private UserEntity user;

    @Autowired
    public User(UserEntity user) {
        this.user = user;
    }

    public String getRoleString() {
        return (user.getAuthorities() != null) ? "Admin" : "Usuario";
    }
}
