package com.weavus.weavus_bankweb.controller.accounts;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountsService accountsService;

    // 口座開設画面に移動。
    @GetMapping("/create")
    public String createAccount(Authentication authentication, Model model) {
        model.addAttribute("account", new AccountsEntity());
        UsersEntity loginUser = (UsersEntity) authentication.getPrincipal();
        model.addAttribute("loginUser", loginUser);
        return "accounts/accounts-create";
    }

    // 口座開設する。
    @PostMapping("/create")
    public String createAccount(@ModelAttribute AccountsEntity account, @RequestParam("passwordCheck") String passwordCheck,
                                Authentication authentication, RedirectAttributes redirectAttributes) {
        try {
            UsersEntity loginUser = (UsersEntity) authentication.getPrincipal();
            account.setUser_id(loginUser.getId());
            accountsService.createAccount(account, passwordCheck);

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/accounts/create";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "わからないエラーが発生しました。");
            return "redirect:/accounts/create";
        }

        return "redirect:/index";
    }
}
