--liquibase formatted sql
--changeset jknap:26
alter table `order` add order_hash varchar(12);