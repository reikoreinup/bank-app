package com.monese.bank.web.controller.account;

import com.monese.bank.web.controller.account.output.AccountOutput;
import com.monese.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
//@Tag(name = "EESSI", description = "EESSI endpoints") todo add swagger
public class AccountController {

    @Autowired
    private AccountService accountService;


    @GetMapping("/{accountId}")
    public AccountOutput getAccount(@PathVariable Long accountId){
        return accountService.findAccount(accountId);
    }


}
