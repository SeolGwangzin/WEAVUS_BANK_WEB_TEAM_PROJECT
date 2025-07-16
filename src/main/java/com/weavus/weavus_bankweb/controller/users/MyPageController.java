package com.weavus.weavus_bankweb.controller.users;

import com.weavus.weavus_bankweb.dto.users.UserUpdateForm;
import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import com.weavus.weavus_bankweb.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final AccountsService accountsService;
    private final UsersService usersService;
    private final UsersInterface usersInterface;

    //mypageに移動。
    @GetMapping("/myPage")
    public String myPageView(Authentication authentication, Model model) {

        UsersEntity loginUser = (UsersEntity) authentication.getPrincipal();

        List<AccountsEntity> accountsEntityList = accountsService.getAllAccounts(loginUser.getId());

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("accountsEntityList", accountsEntityList);

        return "users/myPage";
    }

    //ログアウトする。
//    @GetMapping("/logout")
//    public String myPageView(Authentication authentication) {
//        session.invalidate();
//        return "redirect:/users/user-login";
//    }

    @GetMapping("/update")
    public String showUserUpdatePage(Authentication authentication, Model model) {
        UsersEntity loginUser = (UsersEntity)authentication.getPrincipal();
        if (loginUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", loginUser);
        return "users/user-update";
    }

    @PostMapping("/update")
    public String handleUpdate(UserUpdateForm form, Authentication authentication) {
        UsersEntity loginUser = (UsersEntity) authentication.getPrincipal();
        usersService.handleUpdate(form, loginUser);

        //session更新
        UsersEntity updatedUser = usersInterface.findUserById(loginUser.getId())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりませんでした"));

        //session.setAttribute("loginUser", updatedUser); // ✅ 重新放入 session

        //Spring Securityを使う。
        Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                updatedUser,
                updatedUser.getPassword(),
                authentication.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        return "redirect:/myPage";
    }
}
