package com.weavus.weavus_bankweb.controller.users;

import com.weavus.weavus_bankweb.dto.users.RegisterForm;
import com.weavus.weavus_bankweb.entity.API.ExchangeRateAPIEntity;
import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.API.ExchangeRateAPIService;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import com.weavus.weavus_bankweb.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
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
    public String showIndexPage(@RequestParam(name = "base", required = false, defaultValue = "JPY") String baseCurrency, Authentication authentication, Model model) {
        //ログインチェック
        //UsersEntity loginUser = (UsersEntity) session.getAttribute("loginUser");
        UsersEntity loginUser = (UsersEntity) authentication.getPrincipal();

        if (loginUser == null) {
            return "redirect:/login";
        }

        List<AccountsEntity> accounts = accountsService.getAllAccounts(loginUser.getId());
        ExchangeRateAPIEntity filteredRates = exchangeRateAPIService.getLatestRatesFromJpy(baseCurrency);

        model.addAttribute("accounts" ,accounts);
        model.addAttribute("名前", loginUser.getFull_name());
        model.addAttribute("filteredRates", filteredRates);
        model.addAttribute("currencyList", Arrays.asList("JPY", "USD", "CNY", "KRW", "EUR", "GBP", "CAD", "AUD"));
        return "index";
    }

//    @PostMapping("/login")
//    public String handleLogin(LoginForm loginForm, HttpSession session, Model model) {
//        boolean success = usersService.handleLogin(loginForm);  //ログイン判断
//        if (success) {
//            session.setAttribute("loginUser", usersService.findUserByUsername(loginForm.getUsername()));
//            return "redirect:/index";
//        } else {
//            model.addAttribute("errorMessage", "ログイン失敗！");
//            return "users/user-login";
//        }
//    }

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
