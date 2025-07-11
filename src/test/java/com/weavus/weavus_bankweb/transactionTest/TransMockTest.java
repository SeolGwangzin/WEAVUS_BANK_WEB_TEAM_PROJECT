package com.weavus.weavus_bankweb.transactionTest;

import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.repository.transactions.TransactionsInterface;
import com.weavus.weavus_bankweb.service.transactions.TransactionsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class TransMockTest {

    @Mock
    TransactionsInterface transactionsInterface;

    @InjectMocks
    TransactionsService transactionsService;

    @Test
    void testMock() {
        TransactionsEntity transactionsEntity1 = TransactionsEntity.builder()
                .id(1)
                .fromAccountNumber("123456789012")
                .toAccountNumber("223456789012")
                .type("出金")
                .amount(10000)
                .note("テスト１")
                .balance(120000)
                .build();

        TransactionsEntity transactionsEntity2 = TransactionsEntity.builder()
                .id(3)
                .fromAccountNumber("223456789012")
                .toAccountNumber("123456789012")
                .type("入金")
                .amount(5000)
                .note("テスト２")
                .balance(125000)
                .build();

        List<TransactionsEntity> mockList = List.of(transactionsEntity1, transactionsEntity2);

        when(transactionsInterface.findAll("123456789012")).thenReturn(mockList);

        List<TransactionsEntity> result = transactionsService.getAllTransaction("123456789012");

        assert result.size() == 2;
        assert result.get(0).getAmount() == 10000;
        assert result.get(1).getType().equals("入金");
    }
}
