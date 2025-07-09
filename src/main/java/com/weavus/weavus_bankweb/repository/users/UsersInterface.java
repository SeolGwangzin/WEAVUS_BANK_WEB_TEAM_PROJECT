package com.weavus.weavus_bankweb.repository.users;

import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UsersInterface {
    @Select("SELECT * FROM users WHERE username = #{username}")
    UsersEntity SelectUserByUsername(String username);

    @Insert("""
        INSERT INTO users (
            username,
            password,
            email,
            full_name,
            birth_date,
            gender,
            postal_code,
            prefecture,
            city,
            address_detail,
            phone_number
        )
        VALUES (
            #{username},
            #{password},
            #{email},
            #{full_name},
            #{birth_date},
            #{gender},
            #{postal_code},
            #{prefecture},
            #{city},
            #{address_detail},
            #{phone_number}
        )
        """)
    void createNewUser(UsersEntity user);
}
