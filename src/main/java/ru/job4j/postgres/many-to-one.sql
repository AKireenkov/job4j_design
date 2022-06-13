create table passenger(
id serial primary key,
	name char(255)
	airplane_id int references airplane(id)
);

create table airplane(
id serial primary key,
	name char(255),
);

insert into airplane (name) values ('Pobeda');
insert into passenger (name, airplane_id) values ('Andrey', 1);

select * from airplane ;
select *  from passenger where id in (select id from passenger);