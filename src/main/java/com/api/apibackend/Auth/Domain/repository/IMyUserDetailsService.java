package com.api.apibackend.Auth.Domain.repository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IMyUserDetailsService {
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
