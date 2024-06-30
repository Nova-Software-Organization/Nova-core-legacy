package com.api.apibackend.modules.Auth.Application.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IMyUserDetailsService {
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
