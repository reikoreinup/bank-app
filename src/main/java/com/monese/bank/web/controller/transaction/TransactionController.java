package com.monese.bank.web.controller.transaction;

import com.monese.bank.service.TransactionService;
import com.monese.bank.web.controller.transaction.input.TransactionInput;
import com.monese.bank.web.controller.transaction.output.TransactionsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountNumber}")
    public TransactionsContainer transactionsForAccount(@PathVariable String accountNumber) {
        return transactionService.findTransactionsForAccount(accountNumber);
    }

    @PostMapping
    public void sendMoney(@RequestBody @Valid TransactionInput transactionInput) {
        transactionService.sendMoney(transactionInput);
    }
}
