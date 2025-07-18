package com.weavus.weavus_bankweb.transactionTest;

import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.transactions.TransactionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
@Transactional  // TESTの後ロ-るバック
public class TransTest {

    @Autowired
    TransactionsService transService;


    @Test
    //ERROR TEST
    void transErrorTest(){
        TransactionsEntity transactionsEntity = TransactionsEntity.builder()
                .from_account_number("223456789012")
                .to_account_number("223456789012")
                .amount(10000)
                .note("TEST")
                .build();

        //同じ口座番号を入れる場合
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            transService.createTransaction(transactionsEntity, "1234");
        });
        System.out.println("error メッセージ: " + e.getMessage());

        //PASSWORDが間違った場合
        e = assertThrows(IllegalArgumentException.class, () -> {
            transService.createTransaction(transactionsEntity, "12345678");
        });
        System.out.println("error メッセージ: " + e.getMessage());

        //口座番号先が間違った場合
        e = assertThrows(IllegalArgumentException.class, () -> {
            transactionsEntity.setTo_account_number("333333333333");
            transService.createTransaction(transactionsEntity, "1234");
        });
        System.out.println("error メッセージ: " + e.getMessage());
    }

    @Test
    //振込テスト
    void transTest(){
        TransactionsEntity transactionsEntity = TransactionsEntity.builder()
                .from_account_number("123456789012")
                .to_account_number("223456789012")
                .amount(10000)
                .note("TEST")
                .build();

        transService.createTransaction(transactionsEntity, "1234");

        assertEquals("typeが間違っています。", "出金", transService.getAllTransaction("123456789012").get(0).getType());
    }

}
