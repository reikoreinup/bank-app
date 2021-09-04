package com.monese.bank.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuppressWarnings("JpaDataSourceORMInspection")
@Getter
@Setter
@Entity
@SequenceGenerator(sequenceName = "transaction_id_seq", name = BaseEntity.SEQUENCE_NAME, allocationSize = 1)
public class Transaction extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_account_id")
    private Account fromAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_account_id")
    private Account toAccount;

    private BigDecimal amount;

    private LocalDateTime time;

}
