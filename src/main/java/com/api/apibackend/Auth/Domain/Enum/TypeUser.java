package com.api.apibackend.Auth.Domain.Enum;

public enum TypeUser {
    ROLE_USER(1),
    ROLE_ADMIN(0);

    private int typeUser;
    
    TypeUser(int typeUser) {
        this.typeUser = typeUser;
    }
}
