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

    // 特定口座の履歴を
    public List<TransactionsEntity> getAllTransaction(String accountNumber) {
        return transactionsInterface.findAll(accountNumber);
    }

    // 取引履歴を追加
    @Transactional  // エラーが発生したら戻る。
    public void createTransaction(TransactionsEntity transactionsEntity, String password) {
        int transferAmount = transactionsEntity.getAmount();

        // 入力されなかったり間違って入力した情報があるか検査する。
        if (!StringUtils.hasText(transactionsEntity.getTo_account_number())) {
            throw new IllegalArgumentException("入出金口座を入力してください。");
        }
        if (transactionsEntity.getTo_account_number().equals(transactionsEntity.getFrom_account_number())) {
            throw new IllegalArgumentException("入出金口座が同じです。");
        }
        if (transactionsEntity.getAmount() == null || transactionsEntity.getAmount() <= 0) {
            throw new IllegalArgumentException("振込金額を正しく入力してください。");
        }
        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("口座PASSWORDを入力してください。");
        }

        // 貰う口座の情報がdatabaseにあるか照会する。
        AccountsEntity fromAccount = accountsInterface.findAccountAll(transactionsEntity.getFrom_account_number());
        AccountsEntity toAccount = accountsInterface.findAccountAll(transactionsEntity.getTo_account_number());

        if(fromAccount == null){
            throw new IllegalArgumentException("出金口座が存在しません");
        }

        if(toAccount == null){
            throw new IllegalArgumentException("入金口座が存在しません");
        }

        // 口座パスワード確認
        if (!fromAccount.getPassword().equals(password)) {
            throw new IllegalArgumentException("口座パスワードが正しくありません");
        }

        // 振込金額が残高より高いか確認。
        if (fromAccount.getBalance() < transferAmount) {
            throw new IllegalArgumentException("残高が不足しています");
        }

        // 振込元の残高修正。
        int fromBalance = accountsInterface.findBalanceByBalanceId(transactionsEntity.getFrom_account_number()) - transferAmount;
        fromAccount.setBalance(fromBalance);
        accountsInterface.updateBalance(fromAccount.getAccount_number(), fromBalance);

        // 振込元の取引履歴追加。
        TransactionsEntity fromTransaction = TransactionsEntity.builder()
                .from_account_number(transactionsEntity.getFrom_account_number())
                .to_account_number(transactionsEntity.getTo_account_number())
                .type("出金")
                .amount(transferAmount)
                .note(transactionsEntity.getNote())
                .balance(fromBalance)
                .build();
       if(1 != transactionsInterface.insertTransaction(fromTransaction)){
           throw new IllegalArgumentException("口座開設が失敗しました。");
       };

        // 振込先の残高修正。
        int toBalance = accountsInterface.findBalanceByBalanceId(transactionsEntity.getTo_account_number()) + transferAmount;
        toAccount.setBalance(toBalance);
        accountsInterface.updateBalance(toAccount.getAccount_number(), toBalance);

        // 振込元の取引履歴追加。
        TransactionsEntity toTransaction = TransactionsEntity.builder()
                .from_account_number(transactionsEntity.getFrom_account_number())
                .to_account_number(transactionsEntity.getTo_account_number())
                .type("入金")
                .amount(transferAmount)
                .note(transactionsEntity.getNote())
                .balance(toBalance)
                .build();
        if(1 != transactionsInterface.insertTransaction(toTransaction)){
            throw new IllegalArgumentException("口座開設が失敗しました。");
        };

    }

}
