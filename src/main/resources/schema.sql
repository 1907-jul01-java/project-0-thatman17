drop table if exists clients;

create table clients (
id serial primary key,
username text not null,
password text not null
);
insert into
clients(username, password)
values
('thatman17', 'password')