--liquibase formatted sql
--changeset jknap:23
alter table users add hash varchar(120);
--changeset jknap:24
alter table users add hash_date datetime;