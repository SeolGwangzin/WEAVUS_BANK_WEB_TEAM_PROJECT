package com.weavus.weavus_bankweb.service.users;

import com.weavus.weavus_bankweb.dto.users.LoginForm;
import com.weavus.weavus_bankweb.dto.users.RegisterForm;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersInterface usersInterface;
    public boolean handleLogin(LoginForm loginForm) {
        //usernameでユーザーを探す
        UsersEntity user = findUserByUsername(loginForm.getUsername());
        return user != null && user.getPassword().equals(loginForm.getPassword());
    }

    public UsersEntity findUserByUsername(String username) {
        return usersInterface.SelectUserByUsername(username);
    }

    public void handleRegister(RegisterForm registerForm) {
        UsersEntity user = new UsersEntity();
        String fullName = registerForm.getLast_name() + " " + registerForm.getFirst_name();
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());
        user.setEmail(registerForm.getEmail());
        user.setFull_name(fullName);
        user.setBirth_date(registerForm.getBirth_date());
        user.setGender(registerForm.getGender());
        user.setPostal_code(registerForm.getPostal_code());
        user.setPrefecture(registerForm.getPrefecture());
        user.setCity(registerForm.getCity());
        user.setAddress_detail(registerForm.getAddress_detail());
        user.setPhone_number(registerForm.getPhone_number());

        usersInterface.createNewUser(user);
    }
}
