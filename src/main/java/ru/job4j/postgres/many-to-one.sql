create table passenger(
id serial primary key,
	name char(255)
);

create table airplane(
id serial primary key,
	seat_number char(255),
	passenger_id int references passenger(id)
);

insert into passenger (name) values ('Andrey');
insert into airplane (seat_number, passenger_id) values ('1C', 1);

select *  from passenger;
select * from airplane where id in (select id from passenger);