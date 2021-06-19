drop table PLAYERS if exists;
create table PLAYERS
(
    id          bigint auto_increment not null,
    name        varchar(255) not null,
    back_number int          not null,
    primary key (id)
);