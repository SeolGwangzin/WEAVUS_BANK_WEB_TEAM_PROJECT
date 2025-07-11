package com.weavus.weavus_bankweb.controller.users;

import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final AccountsService accountsService;

    @GetMapping("/myPage")
    public String myPageView(HttpSession session, Model model) {

        UsersEntity loginUser = (UsersEntity) session.getAttribute("loginUser");
        System.out.println(loginUser.getId() + "sdasdasd");
        List<AccountsEntity> accountsEntityList = accountsService.getAllAccounts(loginUser.getId());

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("accountsEntityList", accountsEntityList);
        return "users/myPage";
    }

    @GetMapping("/logout")
    public String myPageView(HttpSession session) {
        session.invalidate(); // 현재 세션 무효화 (모든 세션 속성 제거)
        return "redirect:/users/user-login";
    }
}
