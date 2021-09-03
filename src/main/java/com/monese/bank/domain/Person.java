package com.monese.bank.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@SequenceGenerator(sequenceName = "person_id_seq", name = BaseEntity.SEQUENCE_NAME, allocationSize = 1)
public class Person extends BaseEntity{

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Account> accounts;

}
