package com.weavus.weavus_bankweb.service.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.repository.accounts.AccountsInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsInterface accountsInterface;

    //口座番号をランダムで作る。
    private String generateAccountNumber() {
        // 12のみ桁数
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            //最初の番号は０が出ないようにする。
            if (i == 0) {
                sb.append(random.nextInt(9) + 1); // 1~9
            } else {
                sb.append(random.nextInt(10)); // 0~9
            }
        }
        return sb.toString();
    }

    //口座開設
    public void createAccount(AccountsEntity account, String passwordCheck) {
        //すでに口座が二つある場合。
        if(accountsInterface.findAll(account.getUser_id()).size() == 2){
            throw new IllegalArgumentException("口座はすでに2つあります。");
        }

        //口座番号を入れる変数
        String accountNumber = "";

        //同じ口座番号がある場合。
        do {
            accountNumber = generateAccountNumber();
        } while (accountsInterface.findAccountAll(accountNumber) != null);

        //入力しなかった場合。
        if(account.getPassword() == null) {
            throw new IllegalArgumentException("パスワードを入力してください。。");
        }

        //パスワードの長さが4~8の間ではない場合。
        if(!account.getPassword().matches("\\d{4,8}")){
            System.out.println(accountsInterface.findAll(account.getUser_id()).size());
            System.out.println(account.getUser_id());
            throw new IllegalArgumentException("パスワードの長さは4~8の間で、数字のみ可能です。");
        }

        //PASSWORDとPASSWORD確認が間違った場合
        if(!account.getPassword().equals(passwordCheck)){
            throw new IllegalArgumentException("パスワードとパスワード確認が違います。");
        }

        account.setAccount_number(accountNumber);
        account.setUser_id(account.getUser_id());
        account.setBalance(100000); //基本 value。
        accountsInterface.insertAccount(account);
    }

    public List<AccountsEntity> getAllAccounts(int userId) {
        return accountsInterface.findAll(userId);
    }


    public List<String> getAllAccount(int userId) {
        return accountsInterface.findAccountNum(userId);
    }

    //口座番号で探す。
    public AccountsEntity getAccount(String accountNumber) {
        return accountsInterface.findAccountAll(accountNumber);
    }
}
