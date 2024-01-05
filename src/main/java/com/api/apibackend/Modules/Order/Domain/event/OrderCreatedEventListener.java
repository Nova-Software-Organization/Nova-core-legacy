/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Order.Domain.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.api.apibackend.Modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.Modules.Order.infra.persistence.repository.OrderRepository;
import com.api.apibackend.Shared.Event.Domain.IDomainEventListener;

@Component
public class OrderCreatedEventListener implements IDomainEventListener<OrderCreatedEvent> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderCreatedEventListener(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @EventListener
    public void onEvent(OrderCreatedEvent event) {
        Long createdOrder = event.getCreatedOrder();

        OrderEntity orderEntity = orderRepository.findById(createdOrder).orElse(null);
        //chamar o serviço de envio de email após o pedido ser criado
    }
}
