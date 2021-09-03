package com.monese.bank.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@MappedSuperclass
public class BaseEntity {

    public static final String SEQUENCE_NAME = "id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @Column(nullable = false, updatable = false)
    private Long id;
}
