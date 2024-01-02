/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Auth.Domain.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.api.apibackend.Modules.Auth.Infra.persistence.entity.UserEntity;

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
