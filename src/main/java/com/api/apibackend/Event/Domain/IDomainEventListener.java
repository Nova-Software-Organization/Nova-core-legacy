package com.api.apibackend.Event.Domain;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import org.springframework.context.ApplicationEvent;

public interface IDomainEventListener<T extends ApplicationEvent> {
    void onEvent(T event);
}
