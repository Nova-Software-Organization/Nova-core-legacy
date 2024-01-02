package com.api.apibackend.Modules.Transaction.Domain.event;

/**
 * ----------------------------------------------------------------------------
 * Autor: Kaue de Matos
 * Empresa: Nova Software
 * Propriedade da Empresa: Todos os direitos reservados
 * ----------------------------------------------------------------------------
 */

import lombok.Getter;

@Getter
public class TransactionCreated {
    
    private Long createdTransaction;

    private TransactionCreated(Long createdTransaction) {
        this.createdTransaction = createdTransaction;
    }
}
