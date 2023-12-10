package com.api.apibackend.Auth.Domain.authentication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Auth.Domain.model.LoginRequest;
import com.api.apibackend.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Auth.Infra.repository.UserRepository;

import jakarta.validation.Valid;

@Lazy
@Service
public class AuthorizationLogin {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;

    @Autowired
    public AuthorizationLogin(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        AuthenticationManager authenticationManager,
        GeneratedTokenAuthorizationService generatedTokenAuthorizationService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
    }

    public String login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        
        Set<CustomGrantedAuthority> userRoles = userRepository.findByUsername(loginRequest.getUsername()).getRoles();
        Set<CustomGrantedAuthority> customAuthorities = convertRolesToCustomAuthorities(userRoles);
        return generatedTokenAuthorizationService.generateToken(loginRequest.getUsername(), customAuthorities);
    }

    private Set<CustomGrantedAuthority> convertRolesToCustomAuthorities(Set<CustomGrantedAuthority> roles) {
        Set<CustomGrantedAuthority> authorities = new HashSet<>();
        if (roles != null) {
            for (CustomGrantedAuthority role : roles) {
                authorities.add(role);
            }
        }
        return authorities;
    }
}
