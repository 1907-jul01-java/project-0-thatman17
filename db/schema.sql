drop table if exists clients;
create table clients (
  id serial primary key,
  username text not null,
  password text not null,
  validate boolean not null
);
insert into
  clients(username, password, validate)
values
  ('thatman17', 'p4ssw0rd', 'f');
insert into
clients(username, password, validate)
values
('check', 'test', 't');