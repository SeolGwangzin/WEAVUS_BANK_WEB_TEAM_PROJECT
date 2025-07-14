package com.weavus.weavus_bankweb.usersTest;

import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class UsersInterfaceTest {
    @Autowired
    private UsersInterface usersInterface;

    @Test
    public void testFindUserByUsername_afterSave_shouldReturnCorrectUser() {
        //UsersEntityを準備
        UsersEntity user = new UsersEntity();
        user.setUsername("abc123");
        user.setPassword("abc123");
        user.setEmail("123@gmail.com");
        user.setFull_name("田中次良");
        user.setBirth_date(LocalDate.parse("2025-07-07"));
        user.setGender("女");
        user.setPostal_code("1231231");
        user.setPrefecture("千葉県");
        user.setCity("XXX市");
        user.setAddress_detail("XXXビル");
        user.setPhone_number("02012341234");

        //databaseにinsert
        usersInterface.save(user);
        UsersEntity foundUser = usersInterface.FindUserByUsername(user.getUsername());

        assertEquals("abc123", foundUser.getUsername());
        assertEquals("abc123", foundUser.getPassword());
        assertEquals("123@gmail.com", foundUser.getEmail());
        assertEquals("田中次良", foundUser.getFull_name());
        assertEquals("2025-07-07", foundUser.getBirth_date().toString());
        assertEquals("女", foundUser.getGender());
    }


}
