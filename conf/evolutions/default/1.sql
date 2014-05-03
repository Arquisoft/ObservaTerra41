# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table country (
  code                      varchar(255) not null,
  name                      varchar(255),
  constraint pk_country primary key (code))
;

create table indicator (
  code                      varchar(255) not null,
  name                      varchar(255),
  constraint pk_indicator primary key (code))
;

create table observation (
  id                        bigint not null,
  date                      timestamp,
  obs_value                 double,
  country_code              varchar(255),
  indicator_code            varchar(255),
  constraint pk_observation primary key (id))
;

create table url_repository (
  id                        bigint not null,
  url                       varchar(255),
  constraint pk_url_repository primary key (id))
;

create table user (
  dtype                     varchar(10) not null,
  id                        bigint not null,
  user_name                 varchar(255),
  name                      varchar(255),
  surname                   varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  constraint pk_user primary key (id))
;

create sequence country_seq;

create sequence indicator_seq;

create sequence observation_seq;

create sequence url_repository_seq;

create sequence user_seq;

alter table observation add constraint fk_observation_country_1 foreign key (country_code) references country (code) on delete restrict on update restrict;
create index ix_observation_country_1 on observation (country_code);
alter table observation add constraint fk_observation_indicator_2 foreign key (indicator_code) references indicator (code) on delete restrict on update restrict;
create index ix_observation_indicator_2 on observation (indicator_code);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists country;

drop table if exists indicator;

drop table if exists observation;

drop table if exists url_repository;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists country_seq;

drop sequence if exists indicator_seq;

drop sequence if exists observation_seq;

drop sequence if exists url_repository_seq;

drop sequence if exists user_seq;

