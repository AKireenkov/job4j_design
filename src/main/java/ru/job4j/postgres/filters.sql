create table type (
    id serial primary key,
    name varchar(255)
);

create table product (
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Мороженное');
insert into type (name) values ('Масло');

insert into product (name, type_id, expired_date, price) values ('Сыр плавленный', 1, '2022-06-10', 100);
insert into product (name, type_id, expired_date, price) values ('Сыр косичка', 1, '2023-01-01', 300);
insert into product (name, type_id, expired_date, price) values ('Молоко пастеризованное', 2, '2022-06-20', 90);
insert into product (name, type_id, expired_date, price) values ('Молоко ультрапастеризованное', 2, '2022-08-20', 150);
insert into product (name, type_id, expired_date, price) values ('Молоко 0,5', 2, '2022-06-20', 80);
insert into product (name, type_id, expired_date, price) values ('Мороженое рожок', 3, '2022-10-01', 50);
insert into product (name, type_id, expired_date, price) values ('Мороженое лакомка', 3, '2022-11-02', 99);
insert into product (name, type_id, expired_date, price) values ('Мороженое в стаканчике', 3, '2022-12-03', 100);
insert into product (name, type_id, expired_date, price) values ('Мороженое все', 3, '2020-10-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_1', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_2', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_3', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_4', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_5', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_6', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_7', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_8', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_9', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_10', 4, '2023-01-01', 50);
insert into product (name, type_id, expired_date, price) values ('Масло_11', 4, '2023-01-01', 50);

select * from product as p
inner join type as t on p.type_id = t.id
where t.name = 'Сыр';

select * from product as p
where p.name LIKE '%Мороженое%';

select * from product as p
where p.expired_date < current_date;

select *
from product as p
where p.price in (select max(price) from product);

select t.name as имя_типа, count(p.name) as количество
from product as p
inner join type as t on p.type_id = t.id
group by t.name;

select t.name as имя_типа, count(p.name) as количество
from product as p
inner join type as t on p.type_id = t.id
where t.name = 'Сыр' Or t.name = 'Молоко'
group by t.name;

select t.name as имя_типа, count(p.name) as количество
from product as p
inner join type as t on p.type_id = t.id
group by t.name
having count(p.name) < 10;

select t.name as тип, p.name as имя_продукта
from product as p
inner join type as t on p.type_id = t.id;