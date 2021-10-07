create table player
(
    id          bigint  not null auto_increment,
    back_number integer not null,
    name        varchar(255),
    primary key (id)
);