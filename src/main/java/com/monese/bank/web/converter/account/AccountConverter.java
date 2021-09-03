package com.monese.bank.web.converter.account;

import com.monese.bank.domain.Account;
import com.monese.bank.web.controller.account.output.AccountOutput;
import com.monese.bank.web.converter.BaseConverter;

public class AccountConverter extends BaseConverter<Account, AccountOutput> {

    @Override
    protected void convertInner(Account source, AccountOutput destination) {

    }

    @Override
    protected AccountOutput newObject() {
        return new AccountOutput();
    }
}
