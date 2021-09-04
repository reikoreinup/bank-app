package com.monese.bank.web.controller.transaction.output;

import com.monese.bank.domain.constants.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionInfo {

    private TransactionType transactionType;

    private String counterParty;

    private BigDecimal amount;

    private LocalDateTime time;
}
