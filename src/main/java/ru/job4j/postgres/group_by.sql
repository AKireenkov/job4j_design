create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('phone', 1300);
insert into devices (name, price) values ('phone_1', 2200);
insert into devices (name, price) values ('laptop', 20220);
insert into devices (name, price) values ('computer', 5600);
insert into devices (name, price) values ('headphones', 500);
insert into devices (name, price) values ('phone_2', 900);

insert into people (name) values ('Andrey');
insert into people (name) values ('Petr');
insert into people (name) values ('Ivan');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (4, 1);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (5, 2);
insert into devices_people (device_id, people_id) values (6, 3);

select avg(price) from devices;

select p.name as Имя, avg(d.price)
from people as p
inner join devices_people as dp on dp.people_id = p.id
inner join devices as d on dp.device_id = d.id
group by p.name;

select p.name as Имя, avg(d.price)
from people as p
inner join devices_people as dp on dp.people_id = p.id
inner join devices as d on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000;