--1--
create table departments (
    id serial primary key,
    name text
);

create table employees (
    id serial primary key,
    name text,
    departments_id int references departments(id)
);

insert into departments (name) values ('Accounting');
insert into departments (name) values ('HR');
insert into departments (name) values ('IT');
insert into departments (name) values ('Driver');

insert into employees (name, departments_id) values ('Andrey', 1);
insert into employees (name, departments_id) values ('Petr', 2);
insert into employees (name, departments_id) values ('Ivan', 3);

--2--
select *
from departments as d
left join employees as e
on d.id = e.departments_id;

select *
from departments as d
right join employees as e
on d.id = e.departments_id;

select *
from departments as d
cross join employees as e;

--3--
select *
from departments as d
left join employees as e
on d.id = e.departments_id
where e.departments_id is null;

--4--
select d.id, d.name, e.departments_id, e.id, e.name
from departments as d
left join employees as e
on d.id = e.departments_id;

select d.id, d.name, e.departments_id, e.id, e.name
from employees  as e
right join departments as d
on d.id = e.departments_id;

--5--
create table teens (
    id serial primary key,
    name text,
    gender text
);

insert into teens (name, gender) values ('Andrey', 'M');
insert into teens (name, gender) values ('Petr', 'M');
insert into teens (name, gender) values ('Elena', 'F');
insert into teens (name, gender) values ('Nastya', 'F');

select t1.name, t2.gender
from teens as t1
cross join teens t2;