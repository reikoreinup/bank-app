CREATE SEQUENCE person_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE account_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;
CREATE SEQUENCE transaction_id_seq START WITH 1 INCREMENT BY 1 MINVALUE 1;

CREATE TABLE person (
                        id INT NOT NULL,
                        first_name VARCHAR(100) NOT NULL,
                        last_name VARCHAR(100),
                        email VARCHAR(100) NOT NULL
);

CREATE TABLE account (
                        id INT NOT NULL,
                        name VARCHAR(30) NOT NULL,
                        amount decimal,
                        person_id INT NOT NULL,
                        foreign key (person_id) references person(id)
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

insert into person(id, first_name, last_name, email) values ( person_id_seq.nextval, 'reiko', 'reinup', 'reiko@reiko.ee');
insert into account(id, name, amount, person_id) values (account_id_seq.nextval, 'name', 1000.0, person_id_seq.currval);
