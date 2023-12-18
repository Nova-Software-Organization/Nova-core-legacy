package com.api.apibackend.Gateway.Infra.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.apibackend.Order.Domain.model.TransactionRequest;

// @FeignClient(name = "gateway", url = "http://localhost:8082/v1/gateway/pagseguro")
public interface GatewayServiceClient {
    
    @PostMapping(name = "/enviar")
    ResponseEntity<TransactionRequest> createTransaction(TransactionRequest transactionRequest);
}
