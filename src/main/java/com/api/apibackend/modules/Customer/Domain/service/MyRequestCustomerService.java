/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */
package com.api.apibackend.modules.Customer.Domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.api.apibackend.modules.Customer.Infra.persistence.entity.CustomerEntity;
import com.api.apibackend.modules.CustomerAddress.Infra.persistence.entity.CustomerAddressEntity;
import com.api.apibackend.modules.Order.infra.persistence.entity.OrderEntity;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;

@Service
public class MyRequestCustomerService {

    public ResponseEntity<Map<String, Object>> buildUserResponse(CustomerEntity client) {
        CustomerAddressEntity address = client.getAddress();
        List<OrderEntity> orders = client.getOrders();
        List<ProductEntity> products = new ArrayList<>();

        for (OrderEntity order : orders) {
            products.addAll(order.getProducts());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("client", client);
        response.put("address", address);
        response.put("ordersRequest", orders);
        response.put("products", products);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> buildUserResponse(Optional<CustomerEntity> client) {
        return null;
    }
}