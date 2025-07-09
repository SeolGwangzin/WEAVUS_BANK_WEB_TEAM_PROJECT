package com.weavus.weavus_bankweb.controller.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    // 계좌 등록 화면 보여주기 (GET /account/new)
    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("account", new AccountsEntity());
        return "accounts/account-form"; // account-form.html 렌더링
    }

    // 계좌 등록 처리 (POST /account/save)
    @PostMapping("/create")
    public String createAccount(@ModelAttribute AccountsEntity account) {
        accountsService.createAccount(account);
        return "redirect:/accounts/list";
    }

    // 계좌 목록 보여주기 (GET /account/list)
//    @GetMapping("/list")
//    public String listAccounts(Model model) {
//        model.addAttribute("accounts", accountsService.getAllAccounts());
//        return "accounts/list";
//    }
}
