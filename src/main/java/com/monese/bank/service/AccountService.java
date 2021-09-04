package com.monese.bank.service;

import com.monese.bank.domain.Account;
import com.monese.bank.repository.account.AccountRepository;
import com.monese.bank.repository.account.predicate.AccountPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findAccount(Long id){
        return accountRepository.findOneOrNull(id);
    }

    public Account findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findOneOrNull(AccountPredicate.byAccountNumber(accountNumber));
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public Iterable<Account> saveAll (List<Account> accounts) {
        return accountRepository.saveAll(accounts);
    }
}
