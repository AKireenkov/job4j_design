create table users (
    id serial primary key,
    name varchar(50),
    email varchar(50),
    age int
);

insert into users (name, email, age) values ('Petr', 'test@gmail.com', 35);
insert into users (name, email, age) values ('Andrey', 'kireenkov@yandex.ru', 22);
insert into users (name, email, age) values ('Ivan', 'ivanov@mail.ru', 18);

--Начало транзакции
begin transaction;
--добавление ещё одной строки
insert into employees (name, role, salary) values ('Petr', 'QA', 100);
--добавление первой точки
savepoint first;
--изменение таблицы
delete from employees  where salary=500;
insert into employees (name, role, salary) values ('Efim', 'QA', 700);
--добавление второй точки
savepoint second;
--изменение таблицы
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  9 | Vlad   | PO         |    800000
 11 | Petr   | QA         |       100
 12 | Efim   | QA         |       700

 insert into employees (name, role, salary) values ('Vadim', 'programmer', 900);
 --добавление третьей точки
 savepoint third;
 --изменение таблицы
 delete from employees  where role='QA';
 select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  9 | Vlad   | PO         |    800000
 13 | Vadim  | programmer |       900
 --откатываемся на предыдущую точку и проверяем таблицу
rollback to second;
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  9 | Vlad   | PO         |    800000
 11 | Petr   | QA         |       100
 12 | Efim   | QA         |       700
--еще раз добавляем третью точку
savepoint third;
--удаляем все строки в таблице
delete from employees;
select * from employees;
 id | name | role | salary
----+------+------+--------
(0 rows)
--откатываемся к первой точке, проверям таблицу
rollback to first;
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  9 | Vlad   | PO         |    800000
  7 | Ivan   | analytic   |       500
 11 | Petr   | QA         |       100
(4 rows)

