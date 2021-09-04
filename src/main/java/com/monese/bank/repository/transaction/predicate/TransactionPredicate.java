package com.monese.bank.repository.transaction.predicate;

import com.monese.bank.domain.QTransaction;
import com.querydsl.core.types.Predicate;


public class TransactionPredicate {

    public static final QTransaction transaction = QTransaction.transaction;

    private TransactionPredicate() {}

    public static Predicate byFromOrToAccount(Long accountId) {
        return transaction.fromAccount.id.eq(accountId).or(transaction.toAccount.id.eq(accountId));
    }

    public static Predicate byFromAccount(Long accountId) {
        return transaction.fromAccount.id.eq(accountId);
    }

    public static Predicate byToAccount(Long accountId) {
        return transaction.toAccount.id.eq(accountId);
    }
}
