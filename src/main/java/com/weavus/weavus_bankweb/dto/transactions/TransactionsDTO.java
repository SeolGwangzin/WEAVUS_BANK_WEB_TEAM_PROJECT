package com.weavus.weavus_bankweb.dto.transactions;

import lombok.Data;

@Data
public class TransactionsDTO {
    int fromAccountId;
    int toAccountId;
    int money;
}
