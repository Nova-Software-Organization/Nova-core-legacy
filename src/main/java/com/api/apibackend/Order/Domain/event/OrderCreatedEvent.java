package com.api.apibackend.Order.Domain.event;

import java.time.LocalDate;

import org.springframework.context.ApplicationEvent;

public class OrderCreatedEvent extends ApplicationEvent {
    private final Long createdOrder;
    private final LocalDate dateCreated;

    public OrderCreatedEvent(Object source, Long createdOrder) {
        super(source);
        this.createdOrder = createdOrder;
        this.dateCreated = LocalDate.now();
    }

    public Long getCreatedOrder() {
        return createdOrder;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}