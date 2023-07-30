--liquibase formatted sql
--changeset jknap:25
update payment set default_payment=false;
insert into payment(name, type, default_payment, note)
values ('Płatność online Przelewy 24', 'P24_ONLINE', true, '');