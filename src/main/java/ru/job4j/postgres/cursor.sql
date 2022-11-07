create table products (
    id serial primary key,
    name varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price) VALUES ('product_1', 1, 5);
insert into products (name, count, price) VALUES ('product_2', 2, 10);
insert into products (name, count, price) VALUES ('product_3', 3, 15);
insert into products (name, count, price) VALUES ('product_4', 4, 20);
insert into products (name, count, price) VALUES ('product_5', 5, 25);
insert into products (name, count, price) VALUES ('product_6', 6, 30);
insert into products (name, count, price) VALUES ('product_7', 7, 35);
insert into products (name, count, price) VALUES ('product_8', 8, 40);
insert into products (name, count, price) VALUES ('product_9', 9, 45);
insert into products (name, count, price) VALUES ('product_10', 10, 50);
insert into products (name, count, price) VALUES ('product_11', 11, 55);
insert into products (name, count, price) VALUES ('product_12', 12, 60);
insert into products (name, count, price) VALUES ('product_13', 13, 65);
insert into products (name, count, price) VALUES ('product_14', 14, 70);
insert into products (name, count, price) VALUES ('product_15', 15, 75);
insert into products (name, count, price) VALUES ('product_16', 16, 80);
insert into products (name, count, price) VALUES ('product_17', 17, 85);
insert into products (name, count, price) VALUES ('product_18', 18, 90);
insert into products (name, count, price) VALUES ('product_19', 19, 95);
insert into products (name, count, price) VALUES ('product_20', 20, 100);

--начало транзакции
begin transaction;
--объявление курсора с возможностью перемещать курсор в обратном направлении
declare cursor_backward scroll cursor for select * from products;
--прочитать следующую строку, т.е. первую
fetch from cursor_backward;
 id |   name    | count | price
----+-----------+-------+-------
  1 | product_1 |     1 |     5
(1 row)
--переместить курсор к последней строке
move last from cursor_backward;
--прочитать предыдущую строку(предпоследнюю) от текущей(последней)
fetch backward from cursor_backward;
 id |    name    | count | price
----+------------+-------+-------
 19 | product_19 |    19 |    95
(1 row)
--повторить действие
fetch backward from cursor_backward;
 id |    name    | count | price
----+------------+-------+-------
 18 | product_18 |    18 |    90
(1 row)
--переместить курсор через 2 строки назад
move backward 2  from cursor_backward;
--прочитать предыдущую строку
fetch backward from cursor_backward;
 id |    name    | count | price
----+------------+-------+-------
 15 | product_15 |    15 |    75
(1 row)
--прочитать следущую строку
fetch from cursor_backward;
 id |    name    | count | price
----+------------+-------+-------
 16 | product_16 |    16 |    80
(1 row)
--закрыте курсора
close cursor_backward;
--коммит транзакции
commit;
