package com.api.apibackend.Auth.Domain.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum TypeUser implements GrantedAuthority {
    ROLE_USER(0),
    ROLE_ADMIN(1);

    private int typeUser;
    
    TypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
