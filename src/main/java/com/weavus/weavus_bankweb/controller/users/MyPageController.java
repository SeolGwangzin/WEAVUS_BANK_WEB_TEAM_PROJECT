package com.weavus.weavus_bankweb.controller.users;

import com.weavus.weavus_bankweb.dto.users.UserUpdateForm;
import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.repository.users.UsersInterface;
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
public class MyPageController {

    private final AccountsService accountsService;
    private final UsersService usersService;
    private final UsersInterface usersInterface;

    //mypageに移動。
    @GetMapping("/myPage")
    public String myPageView(HttpSession session, Model model) {

        UsersEntity loginUser = (UsersEntity) session.getAttribute("loginUser");
        System.out.println(loginUser.getId() + "sdasdasd");
        List<AccountsEntity> accountsEntityList = accountsService.getAllAccounts(loginUser.getId());

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("accountsEntityList", accountsEntityList);
        return "users/myPage";
    }

    //ログアウトする。
    @GetMapping("/logout")
    public String myPageView(HttpSession session) {
        session.invalidate();
        return "redirect:/users/user-login";
    }

    @GetMapping("/update")
    public String showUserUpdatePage(HttpSession session, Model model) {
        UsersEntity loginUser = (UsersEntity)session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", loginUser);
        return "users/user-update";
    }

    @PostMapping("/update")
    public String handleUpdate(UserUpdateForm form,HttpSession session) {
        UsersEntity loginUser = (UsersEntity) session.getAttribute("loginUser");
        usersService.handleUpdate(form, loginUser);

        //session更新
        UsersEntity updatedUser = usersInterface.findUserById(loginUser.getId())
                .orElseThrow(() -> new RuntimeException("ユーザーが見つかりませんでした"));

        session.setAttribute("loginUser", updatedUser); // ✅ 重新放入 session
        return "redirect:/myPage";
    }
}
