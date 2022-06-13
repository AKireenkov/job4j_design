create table workers( 
	id serial primary key,
	name varchar(255),
	birthday date,
	age integer,
	gender character
);

insert into workers (name, birthday, age, gender) values ('Andrey', '1999-01-08', 27, 'M');

update workers set name = 'Petr', age = 44 where id = 1;

delete from workers;