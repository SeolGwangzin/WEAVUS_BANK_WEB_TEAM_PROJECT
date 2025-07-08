package com.weavus.weavus_bankweb.controller.transactions;

import com.weavus.weavus_bankweb.dto.transactions.TransactionsDTO;
import com.weavus.weavus_bankweb.entity.accounts.AccountsEntity;
import com.weavus.weavus_bankweb.entity.transactions.TransactionsEntity;
import com.weavus.weavus_bankweb.service.accounts.AccountsService;
import com.weavus.weavus_bankweb.service.transactions.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/trans")
public class TransactionsController {

    private final TransactionsService transactionsService;
    private final AccountsService accountsService;

    //지정된 계좌의 거래 내역 기록 보여주기
    @GetMapping("/list")
    public String transactionList(@ModelAttribute int accountId, Model model) {
            model.addAttribute("trans", transactionsService.getAllTransaction(accountId));
            return "transactions/transaction-history";
    }

    //거래내역 추가하기
    @PostMapping("/create")
    public String createTransaction(@ModelAttribute TransactionsEntity transaction) {
        transactionsService.createTransaction(transaction);
        return "redirect:/transactions/list";
    }
}
