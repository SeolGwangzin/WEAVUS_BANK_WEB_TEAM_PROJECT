package com.weavus.weavus_bankweb.controller.transactions;

import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.entity.users.UsersEntity;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import com.weavus.weavus_bankweb.service.transactions.TransactionsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trans")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final AccountsService accountsService;

    //지정된 계좌의 거래 내역 기록 보여주기
    @GetMapping("/list")
    public String transactionList(@RequestParam("accountNumber") String accountNumber, Model model) {
            model.addAttribute("trans", transactionsService.getAllTransaction(accountNumber));
            model.addAttribute("account", accountsService.getAccount(accountNumber));
            return "transactions/transaction-history";
    }

    //이체 화면 이동
    @GetMapping("/create")
    public String createTransactionForm(HttpSession session, Model model) {
        System.out.println(((UsersEntity) session.getAttribute("loginUser")).getId() + "sadasdadad");
        List<String> accountList = accountsService.getAllAccount(((UsersEntity) session.getAttribute("loginUser")).getId());

        model.addAttribute("transaction", new TransactionsEntity());
        model.addAttribute("accountList", accountList);

        return "transactions/transfer";
    }

    //try catch문을 위한 RedirectAttributes 추가.
    //이체하기
    @PostMapping("/create")
    public String createTransaction(@ModelAttribute TransactionsEntity transaction, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        try {
            transactionsService.createTransaction(transaction, password);
        }catch (IllegalArgumentException e){
            //지정한 예외 처리들
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/trans/create";
        }catch (Exception e) {
            //예상치 못한 다른 모든 예외 처리
            redirectAttributes.addFlashAttribute("errorMessage", "알 수 없는 오류가 발생했습니다.");
            return "redirect:/trans/create";
        }
        //성공
        return "redirect:/trans/list?accountNumber=" + transaction.getFrom_account_number();
    }


}
