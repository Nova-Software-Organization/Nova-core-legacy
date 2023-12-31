package com.api.apibackend.Customer.Domain.event;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import java.time.LocalDate;

import org.springframework.context.ApplicationEvent;

public class CustomerCreated extends ApplicationEvent {

    private final Long createdClientId;
    private final LocalDate dateCreated;

    public CustomerCreated(Object source, Long createdClientId) {
        super(source);
        this.createdClientId = createdClientId;
        this.dateCreated = LocalDate.now();
    }

    public Long getCreatedClientId() {
        return createdClientId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}
