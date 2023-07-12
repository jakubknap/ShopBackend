--liquibase formatted sql
--changeset jknap:9
alter table review add moderated boolean default false;