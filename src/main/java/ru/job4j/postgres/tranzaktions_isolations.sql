create table employees (
    id serial primary key,
    name varchar(50),
    role varchar(50),
    salary int
);

insert into employees (name, role, salary) values ('Andrey', 'programmer', 999999999);
insert into employees (name, role, salary) values ('Ivan', 'analytic', 100000);
insert into employees (name, role, salary) values ('Semen', 'QA', 200000);

--начало транзакции
begin transaction;

--Выполняем операции по изменению таблицы в транзакции 1, затем, делаем селект для таблицы в транзакции 2
insert into employees (name, role, salary) values ('Denis', 'PO', 800000);
delete from employees where name='Semen';
update employees set salary=222000 where name='Ivan';

--Как результат - видим, что не зафиксированные данные в транзакции 1, не видны в транзакции 2
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  7 | Ivan   | analytic   |    100000
  8 | Semen  | QA         |    200000

--Фиксируем изменения в транзакции 1
commit;

--Выполняем селект в транзакции 2, видим, что таблица выглядит иначе
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  9 | Denis  | PO         |    800000
  7 | Ivan   | analytic   |    222000


--Чтение подтвержденных данных (read committed)
--старт транзации
begin transaction isolation level repeatable read;

--актуальные данные в таблице
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  9 | Denis  | PO         |    800000
  7 | Ivan   | analytic   |    222000

--изменяем строку в транзакции 1, не делая коммит
update employees set name='Vlad' where role='PO';

--изменяем ту же строку в транзакции 2, операция заблокирована, до коммита транзакции 1
update employees set salary=100 where role='PO';

--делаем коммита транзакции 1, для транзакции 2 отображается ошибка, после попытки выполнить операцию
ERROR:  could not serialize access due to concurrent update


--Сериализация
--Старт транзакции
begin transaction isolation level serializable;

--актуальные данные
select * from employees;
 id |  name  |    role    |  salary
----+--------+------------+-----------
  6 | Andrey | programmer | 999999999
  7 | Ivan   | analytic   |    222000
  9 | Vlad   | PO         |    800000

--изменяем данные в транзакции 1
update employees set salary=777 where name='Andrey';
--изменяем данные в транзакции 2
update employees set salary=500  where role='analytic';
--делаем коммит транзакции 2
commit;
--делаем коммит транзакции 1, получаем ошибку
commit;
ERROR:  could not serialize access due to read/write dependencies among transactions
DETAIL:  Reason code: Canceled on identification as a pivot, during commit attempt.
HINT:  The transaction might succeed if retried.