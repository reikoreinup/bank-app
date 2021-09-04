package com.monese.bank.util;

import com.monese.bank.domain.Transaction;
import com.monese.bank.domain.constants.TransactionType;

public class TransactionUtil {

    private TransactionUtil(){

    }

    public static String getCounterParty(Transaction transaction, TransactionType type) {
        if (type == TransactionType.RECEIVED) {
            return transaction.getFromAccount().getAccountNumber();
        } else {
            return transaction.getToAccount().getAccountNumber();
        }
    }

    public static TransactionType getTransactionType(Transaction transaction, Long accountId) {
        if (transaction.getToAccount().getId().equals(accountId)) {
            return TransactionType.RECEIVED;
        } else {
            return TransactionType.SENT;
        }
    }

}
