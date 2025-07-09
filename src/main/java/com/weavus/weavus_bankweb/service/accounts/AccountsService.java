package com.weavus.weavus_bankweb.service.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.repository.accounts.AccountsInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsInterface accountsInterface;

    //계좌번호 생성
    private String generateAccountNumber() {
        // 12자리 랜덤 제작
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

    //계좌 생성
    public void createAccount(AccountsEntity account) {
        //계좌번호를 저장할 객체
        String accountNumber = "";
        while (true) {
            accountNumber = generateAccountNumber();
            if (accountsInterface.findAccountAll(accountNumber) == null){
                break;
            }
        }
        account.setAccountNumber(accountNumber);
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
