--liquibase formatted sql
--changeset jknap:2
alter table product add image varchar(64) after currency;