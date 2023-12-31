package com.api.apibackend.Auth.Application.DTOs;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.util.Set;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Set<CustomGrantedAuthority> roles;
}
