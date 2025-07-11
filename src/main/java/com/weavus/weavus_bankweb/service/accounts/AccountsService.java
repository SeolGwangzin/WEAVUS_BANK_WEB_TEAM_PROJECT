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
            //첫자리는 0이 안나오게
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
        //口座番号を入れる変数
        String accountNumber = "";

        //同じ口座番号がある場合。
        do {
            accountNumber = generateAccountNumber();
        } while (accountsInterface.findAccountAll(accountNumber) != null);

        if(accountsInterface.findAll(account.getUser_id()).size() == 2){
            throw new IllegalArgumentException("口座がも二つあります。");

        }

        if(account.getPassword().length() < 4 ||  account.getPassword().length() > 8){
            throw new IllegalArgumentException("口座番号の桁数は4~8の間にしてください。");
        }

        //PASSWORDとPASSWORD確認が間違った場合
        if(!account.getPassword().equals(passwordCheck)){
            throw new IllegalArgumentException("PASSWORDとPASSWORD確認が違います。");
        }

        account.setAccount_number(accountNumber);
        account.setUser_id(account.getUser_id());
        account.setBalance(100000); //基本に入れる。
        accountsInterface.insertAccount(account);
    }

    public List<AccountsEntity> getAllAccounts(int userId) {
        return accountsInterface.findAll(userId);
    }

    //특정 유저 계좌 번호만 가져옴
    public List<String> getAllAccount(int userId) {
        return accountsInterface.findAccountNum(userId);
    }

    //계좌번호로 찾기
    public AccountsEntity getAccount(String accountNumber) {
        return accountsInterface.findAccountAll(accountNumber);
    }
}
