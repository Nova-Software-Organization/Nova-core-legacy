package com.api.apibackend.Transaction.Domain.Event;

import lombok.Getter;

@Getter
public class TransactionCreated {
    
    private Long createdTransaction;

    private TransactionCreated(Long createdTransaction) {
        this.createdTransaction = createdTransaction;
    }
}
