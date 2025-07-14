package com.weavus.weavus_bankweb.usersTest;

import com.weavus.weavus_bankweb.dto.users.LoginForm;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
import com.weavus.weavus_bankweb.service.users.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UsersInterface usersInterface;

    @InjectMocks
    private UsersService usersService;

    @Test
    public void findUserByUsername_notFound() {
        //記録がない場合
        when(usersInterface.FindUserByUsername("abc")).thenReturn(null);

        UsersEntity result = usersService.findUserByUsername("abc");

        assertNull(result);
    }

    //ログイン機能テスト①：username存在、そしてpassword正しい
    @Test
    public void handleLoginTest_success() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("abc123456");
        loginForm.setPassword("abc123456");

        UsersEntity user = new UsersEntity();
        user.setUsername("abc123456");
        user.setPassword("abc123456");

        when(usersInterface.FindUserByUsername("abc123456")).thenReturn(user);

        boolean result = usersService.handleLogin(loginForm);
        assertTrue(result);
    }

    //ログイン機能テスト②：username存在、password正しくない
    @Test
    public void handleLoginTest_wrongPassword() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("abc123456");
        loginForm.setPassword("wrongPassword");

        UsersEntity user = new UsersEntity();
        user.setUsername("abc123456");
        user.setPassword("abc123456");

        when(usersInterface.FindUserByUsername("abc123456")).thenReturn(user);
        boolean result = usersService.handleLogin(loginForm);
        assertFalse(result);
    }

    //ログイン機能テスト③：username存在しない
    @Test
    public void handleLoginTest_userNotFound() {
        LoginForm loginForm = new LoginForm();
        loginForm.setUsername("any");
        loginForm.setPassword("any");

        when(usersInterface.FindUserByUsername("any")).thenReturn(null);
        boolean result = usersService.handleLogin(loginForm);

        assertFalse(result);
    }


}
