package com.api.apibackend.Auth.Domain.repository;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Auth.Application.DTOs.LoginResponseDTO;
import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Auth.Domain.model.LoginRequest;

import jakarta.validation.Valid;

public interface IAutheticationLogin {
    ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest);
    void validateLoginRequest(LoginRequest loginRequest);
    Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles);
}
