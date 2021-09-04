package com.monese.bank.web.controller.transaction.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class TransactionsContainer {

    private String account;

    private BigDecimal balance;

    private List<TransactionInfo> transactions;

}
