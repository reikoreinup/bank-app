package com.monese.bank.web.controller.account;

import com.monese.bank.web.controller.account.output.AccountInfo;
import com.monese.bank.service.AccountService;
import com.monese.bank.web.converter.account.AccountInfoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountInfoConverter accountConverter;


    @GetMapping("/{accountId}")
    public AccountInfo getAccount(@PathVariable Long accountId){
        return accountConverter.convert(accountService.findAccount(accountId));
    }


}
