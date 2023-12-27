package com.api.apibackend.Auth.Infra.persistence.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.apibackend.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Customer.Infra.persistence.entity.CustomerEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "usuario")
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome de usuário não pode estar em branco")
    @Column(name = "apelido", unique = true)
    private String username;

    @NotBlank(message = "A senha não pode estar em branco")
    @Column(name = "senha")
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<CustomGrantedAuthority> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CustomerEntity customer;

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public Set<CustomGrantedAuthority> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<CustomGrantedAuthority> customGrantedAuthorities) {
        this.roles = customGrantedAuthorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toSet());
    }

    public void setRoles(Boolean isAdmin) {
        this.roles = isAdmin ? Collections.singleton(CustomGrantedAuthority.ADMIN) : Collections.singleton(CustomGrantedAuthority.USER);
    }
}
