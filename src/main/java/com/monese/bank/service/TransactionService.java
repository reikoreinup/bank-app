package com.monese.bank.service;

import com.monese.bank.domain.Account;
import com.monese.bank.domain.Transaction;
import com.monese.bank.domain.constants.ErrorMessage;
import com.monese.bank.domain.constants.TransactionType;
import com.monese.bank.exception.AccountNotFoundException;
import com.monese.bank.exception.ValidationException;
import com.monese.bank.repository.transaction.TransactionRepository;
import com.monese.bank.repository.transaction.predicate.TransactionPredicate;
import com.monese.bank.util.TransactionUtil;
import com.monese.bank.web.controller.transaction.input.TransactionInput;
import com.monese.bank.web.controller.transaction.output.TransactionInfo;
import com.monese.bank.web.controller.transaction.output.TransactionsContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public TransactionsContainer findTransactionsForAccount(String accountNumber) {
        TransactionsContainer transactionsContainer = new TransactionsContainer();

        Account account = accountService.findAccountByAccountNumber(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_DOESNT_EXIST.getDescription(), accountNumber));
        }

        List<TransactionInfo> transactionInfos = transactionRepository
                .findAllList(TransactionPredicate.byFromOrToAccount(account.getId())).stream()
                .map(transaction -> mapToTransactionInfo(transaction, TransactionUtil.getTransactionType(transaction, account.getId())))
                .sorted(Comparator.comparing(TransactionInfo::getTime))
                .collect(Collectors.toList());

        transactionsContainer.setAccount(account.getAccountNumber());
        transactionsContainer.setBalance(account.getAmount());
        transactionsContainer.setTransactions(transactionInfos);

        return transactionsContainer;
    }

    public void sendMoney(TransactionInput transactionInput) {
        Account fromAccount = accountService.findAccountByAccountNumber(transactionInput.getFromAccountNumber());
        Account toAccount = accountService.findAccountByAccountNumber(transactionInput.getToAccountNumber());

        validateInput(toAccount, fromAccount, transactionInput);

        fromAccount.setAmount(fromAccount.getAmount().subtract(transactionInput.getAmount()));
        toAccount.setAmount(toAccount.getAmount().add(transactionInput.getAmount()));

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionInput.getAmount());
        transaction.setToAccount(toAccount);
        transaction.setFromAccount(fromAccount);
        transaction.setTime(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    private void validateInput(Account toAccount, Account fromAccount, TransactionInput transactionInput) {
        if (toAccount == null) {
            throw new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_DOESNT_EXIST.getDescription(), transactionInput.getToAccountNumber()));
        }

        if (fromAccount == null) {
            throw new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_DOESNT_EXIST.getDescription(), transactionInput.getFromAccountNumber()));
        }

        if (toAccount.getId().equals(fromAccount.getId())) {
            throw new ValidationException(ErrorMessage.ACCOUNTS_NOT_DIFFERENT.getDescription());
        }

        if (fromAccount.getAmount().compareTo(transactionInput.getAmount()) < 0) {
            throw new ValidationException(String.format(ErrorMessage.INSUFFICIENT_FUNDS.getDescription(), transactionInput.getFromAccountNumber()));
        }
    }

    private TransactionInfo mapToTransactionInfo(Transaction transaction, TransactionType type) {
        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.setTransactionType(type);
        transactionInfo.setCounterParty(TransactionUtil.getCounterParty(transaction, type));
        transactionInfo.setAmount(transaction.getAmount());
        transactionInfo.setTime(transaction.getTime());
        return transactionInfo;
    }
}
