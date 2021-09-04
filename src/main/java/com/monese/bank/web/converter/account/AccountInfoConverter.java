package com.monese.bank.web.converter.account;

import com.monese.bank.domain.Account;
import com.monese.bank.web.controller.account.output.AccountInfo;
import com.monese.bank.web.converter.BaseConverter;
import org.springframework.stereotype.Component;

@Component
public class AccountInfoConverter extends BaseConverter<Account, AccountInfo> {

    @Override
    protected void convertInner(Account account, AccountInfo accountOutput) {
        accountOutput.setAccountNumber(account.getAccountNumber());
        accountOutput.setAmount(account.getAmount());
    }

    @Override
    protected AccountInfo newObject() {
        return new AccountInfo();
    }
}
