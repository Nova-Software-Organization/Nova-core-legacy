/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

package com.api.apibackend.Modules.Gateway.Infra.feign;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.api.apibackend.Modules.Order.Application.DTOs.TransactionRequest;

// @FeignClient(name = "gateway", url = "http://localhost:8082/v1/gateway/pagseguro")
public interface GatewayServiceClient {
    
    @PostMapping(name = "/enviar")
    ResponseEntity<TransactionRequest> createTransaction(TransactionRequest transactionRequest);
}
