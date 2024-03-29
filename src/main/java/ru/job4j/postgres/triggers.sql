create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--1)  Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог на товар
--(нужно прибавить налог к цене товара).
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function tax()
      returns trigger as
  $$
      BEGIN
          update products
          set price = price + price * 0.2
          where id = (select id from inserted);
         return new;
      END;
  $$
  LANGUAGE 'plpgsql';

  create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 8, 100);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 1);

 --2) Триггер должен срабатывать до вставки данных и насчитывать налог на товар (нужно прибавить налог к цене товара).
 --Здесь используем row уровень.
 create or replace function before_insert()
       returns trigger as
   $$
       BEGIN
          new.price = new.price + new.price * 0.2;
          return new;
       END;
   $$
   LANGUAGE 'plpgsql';

  create trigger before_trigger
    before insert
    on products
    for each row
    execute procedure before_insert();

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 8, 100);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 1);

--Нужно написать триггер на row уровне,
--который при вставке продукта в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price.
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create trigger add_trigger
    after insert
    on products
    for each row
    execute procedure add();

create or replace function add()
      returns trigger as
  $$
      BEGIN
        insert into history_of_price (name, price, date) values (new.name, new.price, current_date);
        return new;
      END;
  $$
  LANGUAGE 'plpgsql';

insert into products (name, producer, count, price) VALUES ('product_4', 'producer_4', 8, 700);
insert into products (name, producer, count, price) VALUES ('product_5', 'producer_5', 3, 6);
