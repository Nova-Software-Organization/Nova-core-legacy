/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 * Representa uma entidade de usuario dentro da empresa.
 */

package com.api.apibackend.Modules.Auth.Infra.persistence.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.apibackend.Modules.Auth.Domain.Enum.CustomGrantedAuthority;
import com.api.apibackend.Modules.Customer.Infra.persistence.entity.CustomerEntity;

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
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Representa uma entidade de usuário no sistema.
 */
@Data
@Entity(name = "usuario")
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do usuário.
     */
    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome de usuário.
     */
    @NotBlank(message = "O nome de usuário não pode estar em branco")
    @Column(name = "apelido", unique = true)
    private String username;

    /**
     * Senha do usuário.
     */
    @NotBlank(message = "A senha não pode estar em branco")
    @Column(name = "senha")
    private String password;

    /**
     * Email do usuário.
     */
    @NotBlank(message = "O email não pode estar em branco")
    @Size(max = 100, message = "O email deve ter no máximo 100 caracteres")
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Token de redefinição de senha do usuário.
     */
    @Column(name = "token_redefinicao_senha")
    private String resetPasswordToken;

    /**
     * Expiração do token de renovação de senha do usuário.
     */
    @Column(name = "expiracao_token_redefinicao_senha")
    private LocalDateTime resetPasswordTokenExpiration;

    /**
     * Conjunto de papéis (roles) do usuário.
     */
    @Column(name = "permicao")
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<CustomGrantedAuthority> roles;

    /**
     * Cliente associado ao usuário.
     */
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

    /**
     * Obtém os papéis (roles) do usuário.
     *
     * @return Conjunto de papéis (roles).
     */
    public Set<CustomGrantedAuthority> getRoles() {
        return this.roles;
    }

    /**
     * Define os papéis (roles) do usuário.
     *
     * @param customGrantedAuthorities Conjunto de papéis (roles).
     */
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

    /**
     * Define os papéis (roles) do usuário com base na condição de administrador.
     *
     * @param isAdmin True se o usuário for um administrador, False caso contrário.
     */
    public void setRoles(Boolean isAdmin) {
        this.roles = isAdmin ? Collections.singleton(CustomGrantedAuthority.ADMIN) : Collections.singleton(CustomGrantedAuthority.USER);
    }
}
