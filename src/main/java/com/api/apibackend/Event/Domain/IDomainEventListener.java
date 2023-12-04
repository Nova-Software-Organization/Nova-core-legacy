package com.api.apibackend.Event.Domain;

import org.springframework.context.ApplicationEvent;

public interface IDomainEventListener<T extends ApplicationEvent> {
    void onEvent(T event);
}
