/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.modules.Order.Domain.handler;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.api.apibackend.modules.Customer.Application.DTOs.ClientRequest;
import com.api.apibackend.modules.CustomerAddress.Domain.model.CustomerAddressRequest;
import com.api.apibackend.modules.Order.Application.DTOs.OrderRequest;
import com.api.apibackend.modules.Order.Domain.exception.InsufficientStockException;
import com.api.apibackend.modules.Order.Domain.exception.OrderCannotBeCreatedException;
import com.api.apibackend.modules.Order.Domain.service.OrderCreationService;
import com.api.apibackend.modules.OrderItem.Domain.exception.NonExistentesItemsException;
import com.api.apibackend.modules.OrderItem.Domain.model.OrderItem;
import com.api.apibackend.modules.Product.Infra.persistence.entity.ProductEntity;
import com.api.apibackend.modules.Product.Infra.persistence.repository.ProductRepository;

import jakarta.transaction.Transactional;

@ControllerAdvice
public class OrderRequestControllerHandler {
    private OrderCreationService orderService;
    private ProductRepository productRepository;

    @Autowired
    public OrderRequestControllerHandler(
            OrderCreationService orderCreationService,
            ProductRepository productRepository
    ) {
        this.productRepository = productRepository;
        this.orderService = orderService;
    }

    @Transactional
    public ResponseEntity<String> checkout(OrderRequest orderRequest, CustomerAddressRequest customerAddress,
            ClientRequest clientRequest) throws InsufficientStockException, OrderCannotBeCreatedException, NonExistentesItemsException {
        validateOrder(orderRequest);
        validateClient(clientRequest);
        validateCustomerAddress(customerAddress);

        return orderService.createOrder(orderRequest, customerAddress, clientRequest);
    }

    private void validateOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            throw new IllegalArgumentException("Criação pedido não pode ser feita");
        }

        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("O pedido não contém itens.");
        }

        for (OrderItem item : orderRequest.getItems()) {
            if (item == null) {
                throw new IllegalArgumentException("Um ou mais itens do pedido são inválidos.");
            }

            Long productId = item.getProductId();
            if (productId == null) {
                throw new IllegalArgumentException("O campo productId deve ser especificado para cada item do pedido.");
            }

            Optional<ProductEntity> optionalProduct = productRepository.findById(productId);
            if (!optionalProduct.isPresent()) {
                throw new IllegalArgumentException("Produto não encontrado para o ID: " + productId);
            }

            ProductEntity productEntity = optionalProduct.get();
            int requestedQuantity = item.getQuantity();
            int availableQuantity = productEntity.getStockEntity().getInput_quantity();

            if (requestedQuantity <= 0) {
                throw new IllegalArgumentException("A quantidade do produto deve ser maior que zero.");
            }

            if (requestedQuantity > availableQuantity) {
                throw new IllegalArgumentException("Não há estoque suficiente para o produto com ID: " + productId);
            }
        }
    }

    private void validateClient(ClientRequest clientRequest) {
        if (clientRequest == null) {
            throw new IllegalArgumentException("O objeto ClientRequest não pode ser nulo.");
        }

        if (clientRequest.getName() == null || clientRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Nome' do cliente é obrigatório.");
        }

        if (clientRequest.getEmail() == null || clientRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Email' do cliente é obrigatório.");
        }
    }

    private void validateCustomerAddress(CustomerAddressRequest customerAddress) {
        if (customerAddress == null) {
            throw new IllegalArgumentException("O endereço do cliente não foi especificado.");
        }

        if (customerAddress.getRoad() == null || customerAddress.getRoad().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Rua' no endereço é obrigatório.");
        }

        if (customerAddress.getNeighborhood() == null || customerAddress.getNeighborhood().isEmpty()) {
            throw new IllegalArgumentException("O campo 'Bairro' no endereço é obrigatório.");
        }

        if (customerAddress.getCep() == null || !isValidCep(customerAddress.getCep())) {
            throw new IllegalArgumentException("O CEP fornecido é inválido.");
        }
    }

    private boolean isValidCep(String cep) {
        String cepLimpo = cep.replaceAll("[^0-9]", "");

        return cepLimpo.length() == 8;
    }
}