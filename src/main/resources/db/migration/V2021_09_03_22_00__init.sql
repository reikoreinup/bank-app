CREATE SEQUENCE account_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE transaction_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;

CREATE TABLE account (
                        id INT NOT NULL,
                        account_number VARCHAR(30) NOT NULL,
                        amount decimal
);

create table transaction (
                        id int NOT NULL,
                        from_account_id int NOT NULL,
                        to_account_id int NOT NULL,
                        amount decimal NOT NULL,
                        time timestamp NOT NULL,
                        foreign key (from_account_id) references account(id),
                        foreign key (to_account_id) references account(id)
);

insert into account(id, account_number, amount) values (account_id_seq.nextval, 'EE1', 1000.0);
insert into account(id, account_number, amount) values (account_id_seq.nextval, 'EE2', 1000.0);
