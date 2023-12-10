package com.api.apibackend.Auth.Domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Domain.service.provider.JWTProvider;
import com.api.apibackend.Auth.Domain.token.GeneratedTokenAuthorizationService;
import com.api.apibackend.Auth.Infra.entity.UserEntity;
import com.api.apibackend.Auth.Infra.repository.UserRepository;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserDetailsService {
    
    private UserRepository userRepository;
    private JWTProvider jwtTokenProvider;
    private GeneratedTokenAuthorizationService generatedTokenAuthorizationService;

    @Autowired
    public UserDetailsService(
        UserRepository userRepository,
        JWTProvider jwtProvider,
        GeneratedTokenAuthorizationService generatedTokenAuthorizationService
     ) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtProvider;
        this.generatedTokenAuthorizationService = generatedTokenAuthorizationService;
    }

    public UserEntity createUser(UserEntity userEntity, CustomerEntity customerEntity) {
        userEntity.setCustomer(customerEntity);
        UserEntity newUser = userRepository.save(userEntity);
        return newUser;
    }

    public UserEntity whoami(HttpServletRequest req) {
    return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return generatedTokenAuthorizationService.generateToken(username, userRepository.findByUsername(username).getRoles());
  }
}