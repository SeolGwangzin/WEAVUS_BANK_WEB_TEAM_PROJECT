package com.weavus.weavus_bankweb.controller.users;

import com.weavus.weavus_bankweb.dto.users.LoginForm;
import com.weavus.weavus_bankweb.dto.users.RegisterForm;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.users.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    @GetMapping("/login")
    public String showLoginPage() {
        return "users/user-login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "users/user-create";
    }

    @GetMapping("/index")
    public String showIndexPage(HttpSession session, Model model) {
        //ログインチェック
        UsersEntity loginUser = (UsersEntity) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("名前", loginUser.getFull_name());
        return "index";
    }

    @PostMapping("/login")
    public String handleLogin(LoginForm loginForm, HttpSession session, Model model) {
        boolean success = usersService.handleLogin(loginForm);  //ログイン判断
        if (success) {
            session.setAttribute("loginUser", usersService.findUserByUsername(loginForm.getUsername()));
            return "redirect:/index";
        } else {
            model.addAttribute("errorMessage", "ログイン失敗！");
            return "users/user-login";
        }
    }

    @PostMapping("/register")
    public String handleRegister(RegisterForm registerForm) {
        usersService.handleRegister(registerForm);
        return "redirect:/login";
    }
}
