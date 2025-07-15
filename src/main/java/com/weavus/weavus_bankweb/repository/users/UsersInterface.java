package com.weavus.weavus_bankweb.repository.users;

import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;

@Mapper
public interface UsersInterface {
    @Select("SELECT * FROM users WHERE username = #{username}")
    UsersEntity FindUserByUsername(String username);

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
    void save(UsersEntity user);

    @Select("SELECT * FROM users WHERE id = #{id}")
    Optional<UsersEntity> findUserById(int id);

    @Update("UPDATE users SET full_name = #{full_name}, postal_code = #{postal_code}, prefecture = #{prefecture}, city = #{city}, address_detail = #{address_detail}, phone_number = #{phone_number} WHERE id = #{id}")
    void UpdateUser(UsersEntity user);
}
