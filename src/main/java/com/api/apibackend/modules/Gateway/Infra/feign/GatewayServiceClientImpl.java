// package com.api.apibackend.Modules.Gateway.Infra.feign;

// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import com.api.apibackend.Modules.Order.Domain.model.TransactionRequest;

// @Service
// public class GatewayServiceClientImpl implements GatewayServiceClient {
//     private GatewayServiceClient gatewayServiceClient;

//     public GatewayServiceClientImpl(GatewayServiceClient gatewayServiceClient) {
//         this.gatewayServiceClient = gatewayServiceClient;
//     }

//     /**
//      * TODO
//      * Verificar o tipo da transação antes de chamar o serviço, desta iremos conseguir qual e o tipo de pagamento para chamar o responsavel pelo mesmo assim criando a transação adequada
//      */
//     @Override
//     public ResponseEntity<TransactionRequest> createTransaction(TransactionRequest transactionRequest) { }
// }