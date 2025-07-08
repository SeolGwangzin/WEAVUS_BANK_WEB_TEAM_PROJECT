package com.weavus.weavus_bankweb.service.transactions;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.repository.accounts.AccountsInterface;
import com.weavus.weavus_bankweb.repository.transactions.TransactionsInterface;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final AccountsInterface accountsInterface;
    private final TransactionsInterface transactionsInterface;

    //특정계좌 거래내역 불러오기
    public List<TransactionsEntity> getAllTransaction(int accountId) {
        List<TransactionsEntity> trans = transactionsInterface.findAll(accountId);
        return trans;
    }

    //거래내역 추가
    public void createTransaction(TransactionsEntity transactionsEntity) {
        //보내는 계좌의 잔금 수정
        int fromBalance = accountsInterface.findBalanceByAccountId(transactionsEntity.getFromAccountId()) - transactionsEntity.getAmount();
        AccountsEntity formAccounts = AccountsEntity.builder()
                .accountId(transactionsEntity.getFromAccountId())
                .balance(fromBalance)
                .build();
        accountsInterface.updateBalance(formAccounts);

        //보내는쪽의 거래내역 추가
        transactionsEntity.setType("出金");
        transactionsEntity.setAmount(fromBalance);
        transactionsInterface.insertTransaction(transactionsEntity);


        //받는 계좌의 잔금 수정
        int toBalance = accountsInterface.findBalanceByAccountId(transactionsEntity.getToAccountId()) + transactionsEntity.getAmount();
        AccountsEntity toAccounts = AccountsEntity.builder()
                .accountId(transactionsEntity.getToAccountId())
                .balance(toBalance)
                .build();
        accountsInterface.updateBalance(toAccounts);

        //받는쪽의 거래내역 추가
        transactionsEntity.setType("入金");
        transactionsEntity.setAmount(toBalance);
        transactionsInterface.insertTransaction(transactionsEntity);
    }

}
