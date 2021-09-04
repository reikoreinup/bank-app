package com.monese.bank.web.controller.transaction;

import com.monese.bank.BankApplication;
import com.monese.bank.util.RestAssuredUtil;
import com.monese.bank.web.controller.transaction.input.TransactionInput;
import com.monese.bank.web.controller.transaction.output.TransactionsContainer;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {BankApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransactionControllerTest extends RestAssuredUtil {

    @Test
    void sendMoneyTest() {
        BigDecimal initialBalance = BigDecimal.valueOf(1000);
        BigDecimal amount = BigDecimal.valueOf(333);

        TransactionInput transactionInput = new TransactionInput();
        transactionInput.setAmount(amount);
        transactionInput.setFromAccountNumber("EE1");
        transactionInput.setToAccountNumber("EE2");

        with().body(transactionInput).contentType(ContentType.JSON)
                .post("/bank/transaction").then().assertThat().statusCode(200);

        TransactionsContainer senderTransactionContainer = get("/bank/transaction/EE1")
                .then().assertThat().statusCode(200)
                .extract().as(TransactionsContainer.class);

        TransactionsContainer receiverTransactionContainer = get("/bank/transaction/EE2")
                .then().assertThat().statusCode(200)
                .extract().as(TransactionsContainer.class);

        assertNotNull(senderTransactionContainer);
        assertNotNull(receiverTransactionContainer);
        assertEquals(initialBalance.subtract(amount).longValue(), senderTransactionContainer.getBalance().longValue());
        assertEquals(initialBalance.add(amount).longValue(), receiverTransactionContainer.getBalance().longValue());
        assertEquals(1, senderTransactionContainer.getTransactions().size());
        assertEquals(1, receiverTransactionContainer.getTransactions().size());
    }
}
