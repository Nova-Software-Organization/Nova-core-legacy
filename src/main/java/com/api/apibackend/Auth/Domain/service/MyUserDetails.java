package com.api.apibackend.Auth.Domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.apibackend.Auth.Infra.entity.UserEntity;
import com.api.apibackend.Auth.Infra.repository.UserRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  private final UserRepository userRepository;

  public MyUserDetails(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final UserEntity user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    return org.springframework.security.core.userdetails.User
        .withUsername(username)
        .password(user.getPassword())
        .authorities(user.getRoles())
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }
}
