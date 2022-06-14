create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Fox', 10000, '1980-09-28');
insert into fauna (name, avg_age) values ('bream_fish', 12222);
insert into fauna (name, avg_age) values ('fish_cod', 25555);
insert into fauna (name, avg_age, discovery_date) values ('ell', 20999, '1950-01-01');
insert into fauna (name, avg_age, discovery_date) values ('wolf', 10000, '1933-05-12');
insert into fauna (name, avg_age, discovery_date) values ('cat', 16666, '1977-07-23');
insert into fauna (name, avg_age, discovery_date) values ('duck', 23000, '2000-09-28');
insert into fauna (name, avg_age, discovery_date) values ('dog', 21000, '1999-03-23');
insert into fauna (name, avg_age, discovery_date) values ('cow', 19006, '1900-06-16');
insert into fauna (name, avg_age, discovery_date) values ('Fox', 23333, '1880-05-17');

select * from fauna where name LIKE '%fish%';
select * from fauna where avg_age BETWEEN 10000 AND 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';