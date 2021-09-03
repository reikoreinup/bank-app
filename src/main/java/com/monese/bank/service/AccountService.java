package com.monese.bank.service;

import com.monese.bank.web.controller.account.output.AccountOutput;
import com.monese.bank.domain.Account;
import com.monese.bank.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountOutput findAccount(Long id){
        AccountOutput accountOutput = new AccountOutput();
        Account account = accountRepository.findOne(id);

        accountOutput.setAmount(account.getAmount());
        accountOutput.setName(account.getName());
        accountOutput.setOwner(account.getPerson().getFirstName() + account.getPerson().getLastName());
        return accountOutput;
    }
}
