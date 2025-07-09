package com.weavus.weavus_bankweb.service.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.repository.accounts.AccountsInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsInterface accountsInterface;

    public void createAccount(AccountsEntity account) {
        account.setBalance(100000);
        account.setCreateDate(LocalDateTime.now());
        accountsInterface.insertAccount(account);
    }

    public List<AccountsEntity> getAllAccounts(int userId) {
        return accountsInterface.findAll(userId);
    }
}
