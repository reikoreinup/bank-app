package com.monese.bank.domain.constants;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    ACCOUNT_DOESNT_EXIST("No account with number %s"),
    ACCOUNTS_NOT_DIFFERENT("Accounts must be different"),
    INSUFFICIENT_FUNDS("Insufficient funds for account number %s");

    private final String description;

    ErrorMessage(String description) {
        this.description = description;
    }
}
