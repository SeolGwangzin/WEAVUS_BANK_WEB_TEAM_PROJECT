package com.weavus.weavus_bankweb.entity.accounts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsEntity {
    private int id;            
    private int user_id;
    private String account_number;
    private Integer balance;
    private String password;
    private String purpose;
    private LocalDateTime create_date;
}
