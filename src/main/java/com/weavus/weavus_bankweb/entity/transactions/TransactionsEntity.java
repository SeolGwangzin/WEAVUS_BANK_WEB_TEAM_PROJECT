package com.weavus.weavus_bankweb.entity.transactions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsEntity {
    private int id;
    private String fromAccountId;
    private String toAccountId;
    private String type;        // "入金", "出金"  二つで選択
    private int amount;
    private LocalDateTime date;
    private String note;
}
