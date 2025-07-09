package com.weavus.weavus_bankweb.repository.transactions;

import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TransactionsInterface {

    //거래내역 추가
    @Insert("""
    INSERT into transactions (from_account_id, to_acoount_id, type,
                              amount, note)
    values (#{fromAccountId}, #{toAccountId}, #{type},
            #{amount}, #{note})
""")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertTransaction(TransactionsEntity transaction);

    //지정된 계좌의 전체 거래내역
    @Select("""
        SELECT * FROM transactions where 
                                    (from_account_id = #{accountId} AND type = '出金') 
                                    OR 
                                    (to_acoount_id = #{accountId} AND type = '入金') 
        """)
    List<TransactionsEntity> findAll(int accountId);
}
