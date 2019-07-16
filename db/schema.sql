drop table if exists clients;
drop table if exists employees;

create table clients (
  id serial primary key,
  username text not null,
  password text not null,
  balance integer,
  validate boolean not null
);
insert into
  clients(username, password, validate)
values
  ('thatman17', 'p4ssw0rd', 'f');
insert into
clients(username, password, balance, validate)
values
('check', 'test', 100, 't');

create table employees (
	id serial primary key, 
	username text not null,
	password text not null
);
insert into
employees(username, password)
values
('employee', 'test');