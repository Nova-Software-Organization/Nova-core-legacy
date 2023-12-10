package com.api.apibackend.Auth.Domain.Enum;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomGrantedAuthority implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String authority;

    CustomGrantedAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    @JsonValue
    public String getAuthority() {
        return authority;
    }

    @JsonCreator
    public static CustomGrantedAuthority fromString(String authority) {
        if (authority != null) {
            for (CustomGrantedAuthority customAuthority : CustomGrantedAuthority.values()) {
                if (authority.equalsIgnoreCase(customAuthority.getAuthority())) {
                    return customAuthority;
                }
            }
        }
        
        throw new IllegalArgumentException("Unknown authority: " + authority);
    }
}
