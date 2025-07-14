package com.weavus.weavus_bankweb.controller.users;

import com.weavus.weavus_bankweb.dto.users.LoginForm;
import com.weavus.weavus_bankweb.dto.users.RegisterForm;
import com.weavus.weavus_bankweb.entity.API.ExchangeRateAPIEntity;
import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.API.ExchangeRateAPIService;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import com.weavus.weavus_bankweb.service.users.UsersService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UsersController {
    private final UsersService usersService;
    private final AccountsService accountsService;
    private final ExchangeRateAPIService  exchangeRateAPIService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "users/user-login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "users/user-create";
    }

    @GetMapping("/index")
    public String showIndexPage(HttpSession session, Model model) {
        //ログインチェック
        UsersEntity loginUser = (UsersEntity) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        List<AccountsEntity> accounts = accountsService.getAllAccounts(loginUser.getId());
        ExchangeRateAPIEntity filteredRates = exchangeRateAPIService.getLatestRatesFromJpy();

        model.addAttribute("accounts" ,accounts);
        model.addAttribute("名前", loginUser.getFull_name());
        model.addAttribute("filteredRates", filteredRates);
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
    public String handleRegister(RegisterForm registerForm, Model model) {
        try {
            usersService.handleRegister(registerForm);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMsg", e.getMessage());
            model.addAttribute("registerForm", registerForm);
            return "users/user-create";
        }
    }
}
