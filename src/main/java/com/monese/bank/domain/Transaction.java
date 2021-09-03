package com.monese.bank.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SequenceGenerator(sequenceName = "transaction_id_seq", name = BaseEntity.SEQUENCE_NAME, allocationSize = 1)
public class Transaction extends BaseEntity{

    @ManyToOne
    private Account fromAccount;

    @ManyToOne
    private Account toAccount;

    private BigDecimal amount;

    private LocalDateTime time;

}
