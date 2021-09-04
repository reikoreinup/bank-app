package com.monese.bank.repository.account.predicate;

import com.monese.bank.domain.QAccount;
import com.querydsl.core.types.Predicate;

public class AccountPredicate {

    public static final QAccount account = QAccount.account;

    private AccountPredicate() {}

    public static Predicate byAccountNumber(String accountNumber) {
        return account.accountNumber.eq(accountNumber);
    }
}
