create table person(
id serial primary key,
	name char(255),
	phone_id int references person(id) unique
);

create table phone_number(
id serial primary key,
	number char(255)
);

insert into person (name) values ('Andrey');
insert into person (name) values ('Ivan');
insert into person (name) values ('Alexey');

insert into phone_number (number) values ('7-999-964-44-25');
insert into phone_number (number) values ('7-888-666-33-13');
insert into phone_number (number) values ('7-777-566-58-53');

select * from person;
select * from phone_number;
