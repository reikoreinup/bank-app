package com.monese.bank.web.controller.transaction.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class TransactionInput {

    @NotNull
    private String fromAccountNumber;

    @NotNull
    private String toAccountNumber;

    @NotNull
    private BigDecimal amount;

}
