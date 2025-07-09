package com.weavus.weavus_bankweb.service.transactions;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.repository.accounts.AccountsInterface;
import com.weavus.weavus_bankweb.repository.transactions.TransactionsInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final AccountsInterface accountsInterface;
    private final TransactionsInterface transactionsInterface;

    //특정계좌 거래내역 불러오기
    public List<TransactionsEntity> getAllTransaction(String accountNumber) {
        List<TransactionsEntity> trans = transactionsInterface.findAll(accountNumber);
        return trans;
    }

    //거래내역 추가
    @Transactional  //중간에 오류가 나면 전부 전체 실행 되돌린다.
    public void createTransaction(TransactionsEntity transactionsEntity, String password) {
        int transferAmount = transactionsEntity.getAmount();

        //입력하지 않거나 잘못입력한 정보가 있는지 확인
        if (!StringUtils.hasText(transactionsEntity.getToAccountNumber())) {
            throw new IllegalArgumentException("入出金口座を入力してください。");
        }
        if (transactionsEntity.getToAccountNumber().equals(transactionsEntity.getFromAccountNumber())) {
            throw new IllegalArgumentException("入出金口座が同じです。");
        }
        if (transactionsEntity.getAmount() == null || transactionsEntity.getAmount() <= 0) {
            throw new IllegalArgumentException("振込金額を正しく入力してください。");
        }
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("口座PASSWORDを入力してください。");
        }

        // 받는 계좌가 DB에 있는지 조회.
        AccountsEntity fromAccount = accountsInterface.findAccountAll(transactionsEntity.getFromAccountNumber());
        AccountsEntity toAccount = accountsInterface.findAccountAll(transactionsEntity.getToAccountNumber());

        if(fromAccount == null){
            throw new IllegalArgumentException("出金口座が存在しません");
        }

        if(toAccount == null){
            throw new IllegalArgumentException("入金口座が存在しません");
        }

        //비밀번호 확인 로직
        if (!fromAccount.getPassword().equals(password)) {
            throw new IllegalArgumentException("口座パスワードが正しくありません");
        }

        //이체 금액이 잔고보다 큰지 확인
        if (fromAccount.getBalance() < transferAmount) {
            throw new IllegalArgumentException("残高が不足しています");
        }

        // 출금 계좌의 잔액 수정
        int fromBalance = accountsInterface.findBalanceByBalanceId(transactionsEntity.getFromAccountNumber()) - transferAmount;
        fromAccount.setBalance(fromAccount.getBalance() - transferAmount);
        accountsInterface.updateBalance(fromAccount);

        // 보내는 쪽(출금) 거래내역 추가
        TransactionsEntity withdrawalTransaction = TransactionsEntity.builder()
                .fromAccountNumber(transactionsEntity.getFromAccountNumber())
                .toAccountNumber(transactionsEntity.getToAccountNumber())
                .type("出金")
                .amount(transferAmount)
                .note(transactionsEntity.getNote())
                .balance(fromBalance)
                .build();
        transactionsInterface.insertTransaction(withdrawalTransaction);

        // 받는 계좌의 잔액 수정
        int toBalance = accountsInterface.findBalanceByBalanceId(transactionsEntity.getToAccountNumber()) + transferAmount;
        toAccount.setBalance(toAccount.getBalance() - transferAmount);
        accountsInterface.updateBalance(toAccount);

        // 받는 쪽(입금) 거래내역 추가
        TransactionsEntity depositTransaction = TransactionsEntity.builder()
                .fromAccountNumber(transactionsEntity.getFromAccountNumber())
                .toAccountNumber(transactionsEntity.getToAccountNumber())
                .type("入金")
                .amount(transferAmount)
                .note(transactionsEntity.getNote())
                .balance(toBalance)
                .build();
        transactionsInterface.insertTransaction(depositTransaction);
    }

}
