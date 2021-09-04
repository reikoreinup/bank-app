package com.monese.bank.web.controller.account.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountInfo {

    private BigDecimal amount;

    private String accountNumber;
}
