create table body (
    id serial primary key,
    name text
);

create table engine (
    id serial primary key,
    capacity float
);

create table transmission (
    id serial primary key,
    name text
);

create table car (
    id serial primary key,
    name text,
    id_body int references body(id),
    id_engine int references engine(id),
    id_transmission int references transmission(id)
);

insert into body (name) values ('sedan');
insert into body (name) values ('coupe');
insert into body (name) values ('crossover');
insert into body (name) values ('minivan');

insert into engine (capacity) values (1.4);
insert into engine (capacity) values (1.6);
insert into engine (capacity) values (2.0);
insert into engine (capacity) values (2.2);

insert into transmission (name) values ('automatic');
insert into transmission (name) values ('manual');
insert into transmission (name) values ('hybrid');

insert into car (name, id_body, id_engine, id_transmission) values ('audi a3', 1, 2, 2);
insert into car (name, id_body, id_engine, id_transmission) values ('bmw x5', 3, 3, 1);
insert into car (name, id_body, id_transmission) values ('fiat ducato', 3, 1);
insert into car (name, id_body, id_engine) values ('fiat ducato', 1, 2);

select c.name, b.name, e.capacity, t.name
from car c
left join body b on c.id_body = b.id
left join engine e on c.id_engine = e.id
left join transmission t on c.id_transmission = t.id;

select b.name
from body b
left join car c on c.id_body = b.id
where c.id_body is null;

select e.capacity
from engine e
left join car c on c.id_engine = e.id
where c.id_engine is null;

select t.name
from transmission t
left join car c on c.id_transmission = t.id
where c.id_transmission is null;