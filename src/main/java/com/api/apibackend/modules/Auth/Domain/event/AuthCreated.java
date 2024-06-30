/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Auth.Domain.event;

import java.time.LocalDate;

import org.springframework.context.ApplicationEvent;

public class AuthCreated extends ApplicationEvent {
    private final Long createdUser;
    private final LocalDate dateCreated;

    public AuthCreated(Object source, Long createdUser) {
        super(source);
        this.createdUser = createdUser;
        this.dateCreated = LocalDate.now();
    }

    public Long getCreatedUser() {
        return createdUser;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}
