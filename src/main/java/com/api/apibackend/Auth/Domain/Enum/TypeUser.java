package com.api.apibackend.Auth.Domain.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum TypeUser implements GrantedAuthority {
    ROLE_USER(1),
    ROLE_ADMIN(0);

    private int typeUser;
    
    TypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
