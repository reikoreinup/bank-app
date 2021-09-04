package com.monese.bank.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@SequenceGenerator(sequenceName = "account_id_seq", name = BaseEntity.SEQUENCE_NAME, allocationSize = 1)
public class Account extends BaseEntity{

    private String accountNumber;

    private BigDecimal amount;

}
