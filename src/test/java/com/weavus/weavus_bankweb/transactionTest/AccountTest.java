package com.weavus.weavus_bankweb.transactionTest;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.repository.accounts.AccountsInterface;
import com.weavus.weavus_bankweb.service.transactions.TransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@Transactional
public class AccountTest {

    @Autowired
    private AccountsInterface accountsInterface;


    @Test
    //口座開設TEST
    void createAccountInterfaceTest() {
        AccountsEntity ae = AccountsEntity.builder()
                .user_id(1)
                .account_number("123412341234")
                .balance(10000)
                .password("1234")
                .purpose("Test")
                .build();

        accountsInterface.insertAccount(ae);

        List<AccountsEntity> lae = accountsInterface.findAll(1);
        assertNotNull("NULL", lae.get(0).getAccount_number());
        System.out.println("LAE length: " + lae.size());
        System.out.println("LAE: " + lae.get(0).getAccount_number());
    }

    @Test
    //残高UPDATEテスト
    void balanceTest(){
        accountsInterface.updateBalance("123456789022", 20000);
        assertEquals("残高が間違います。", 20000, accountsInterface.findBalanceByBalanceId("123456789022"));
    }
}
