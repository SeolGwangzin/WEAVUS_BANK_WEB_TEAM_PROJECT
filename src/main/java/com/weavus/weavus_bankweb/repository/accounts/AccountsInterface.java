package com.weavus.weavus_bankweb.repository.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AccountsInterface {

        //口座開設
        @Insert("""
        INSERT INTO accounts (user_id, account_number, balance, password, purpose)
        VALUES (#{user_id}, #{account_number}, #{balance}, #{password}, #{purpose})
    """)
        @Options(useGeneratedKeys = true, keyProperty = "id")
        void insertAccount(AccountsEntity account);

        //選んだユーザーの口座情報を貰う。
        @Select("SELECT * FROM accounts where user_id = #{user_id}")
        List<AccountsEntity> findAll(int user_id);

        //選んだユーザーの口座番号だけ貰う。
        @Select("SELECT account_number FROM accounts where user_id = #{user_id}")
        List<String> findAccountNum(int user_id);

        //口座番号で口座情報を貰う。
        @Select("SELECT * FROM accounts where account_number = #{account_number}")
        AccountsEntity findAccountAll(String account_number);

        //選んだ口座の残高を貰う。
        @Select("SELECT balance FROM accounts where account_number = #{account_number}")
        int findBalanceByBalanceId(String account_number);

        //残高アップデート。
        @Update("UPDATE accounts SET balance = #{balance} WHERE account_number = #{account_number}")
        void updateBalance(String account_number, int balance);
}
