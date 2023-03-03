# create schema if not exists interview_hibernate_demo;

create table students(
  id integer primary key not null AUTO_INCREMENT,
  name varchar(255) not null,
  mark tinyint not null
);