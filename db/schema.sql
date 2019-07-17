drop table if exists clients;
drop table if exists employees;

create table clients (
  id serial primary key,
  username text not null,
  password text not null,
  balance integer,
  validate integer
);
insert into
  clients(username, password)
values
  ('thatman17', 'p4ssw0rd');
insert into
clients(username, password, balance, validate)
values
('check', 'test', 100, '1');
insert into
clients(username, password, balance, validate)
values
('steven', 'ugly', 20000, '1');
insert into
clients(username, password)
values
('Roger', 'complex');

create table employees (
	id serial primary key, 
	username text not null,
	password text not null
);
insert into
employees(username, password)
values
('employee', 'test');

create table admin (
	id serial primary key,
	username text not null,
	password text not null,
	code integer not null
);
insert into
admin(username, password, code)
values
('elia', 'admin', 5432);