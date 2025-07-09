package com.weavus.weavus_bankweb.repository.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountsInterface {

        //새로운 계좌 생성
        @Insert("""
        INSERT INTO accounts (user_id, account_number, balance, password, purpose, create_date)
        VALUES (#{userId}, #{accountId}, #{balance}, #{password}, #{purpose}, #{createDate})
    """)
        @Options(useGeneratedKeys = true, keyProperty = "id")   //id는 자동 증가이므로 삽입 작업후 자동 생성된 id값을 다시 받아옴
        void insertAccount(AccountsEntity account);

        //모든 계좌 정보 불러오기
        @Select("SELECT * FROM accounts where user_id = #{userId}")
        List<AccountsEntity> findAll(int userId);

        //지정 계좌 번호들 불러오기
        @Select("SELECT account_number FROM accounts where user_id = #{userId}")
        List<String> findAccountNum(int userId);

        //지정 비밀번호 불러오기
        @Select("SELECT password FROM accounts where account_number = #{accountNumber}")
        String findPassword(String accountNumber);

        //계좌번호로 지정 계좌 정보 불러오기
        @Select("SELECT * FROM accounts where account_number = #{accountNumber}")
        AccountsEntity findAccountAll(String accountNumber);

        //지정된 계좌의 잔고 가져오기
        @Select("SELECT balance FROM accounts where account_number = #{accountNumber}")
        int findBalanceByBalanceId(String accountId);

        //잔고 업데이트
        @Update("UPDATE accounts SET balance = #{balance} WHERE account_number = #{accountNumber}")
        void updateBalance(AccountsEntity accounts);
}
