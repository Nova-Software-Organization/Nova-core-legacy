/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Auth.Domain.authentication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Modules.Auth.Application.DTOs.response.LoginResponseDTO;
import com.api.apibackend.Modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Modules.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Modules.Auth.Domain.repository.IAutheticationLogin;
import com.api.apibackend.Modules.Auth.Domain.service.AnonymizationService;
import com.api.apibackend.Modules.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Modules.Auth.Infra.persistence.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class AuthorizationLogin implements IAutheticationLogin {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;
    private AnonymizationService anonymizationService;

    @Autowired
    public AuthorizationLogin(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            GeneratedTokenAuthorizationService generatedTokenAuthorizationService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
    }

    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            validateLoginRequest(loginRequest);

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            Set<CustomGrantedAuthority> userRoles = userRepository.findByUsername(loginRequest.getUsername())
                    .getRoles();
            Set<CustomGrantedAuthority> customAuthorities = convertRolesToCustomAuthorities(userRoles);
            String tokenGeneration = generatedTokenAuthorizationService.generateToken(loginRequest.getUsername(),
                    customAuthorities);

            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(tokenGeneration);
            return ResponseEntity.status(HttpStatus.CREATED).body(loginResponseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LoginResponseDTO("Usuário não autorizado! Os campos de usuário e senha são obrigatórios."));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDTO("Usuário ou senha incorretos!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new LoginResponseDTO("Erro ao processar a solicitação de login"));
        }
    }

    public void validateLoginRequest(LoginRequest loginRequest) {
        if (loginRequest == null || loginRequest.getUsername().isEmpty() || loginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Erro: dados de login não fornecidos corretamente");
        }
    }

    public Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles) {
        Set<CustomGrantedAuthority> authorities = new HashSet<>();
        if (roles != null) {
            for (CustomGrantedAuthority role : roles) {
                authorities.add(role);
            }
        }
        return authorities;
    }
}
