CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (name) values ('IBM');
insert into company (name) values ('Oracle');
insert into company (name) values ('Spotify');
insert into company (name) values ('Apple');
insert into company (name) values ('HP');

insert into person (name, company_id) values ('Andrey', 1);
insert into person (name, company_id) values ('Petr', 1);
insert into person (name, company_id) values ('Dmitriy', 1);
insert into person (name, company_id) values ('Denis', 2);
insert into person (name, company_id) values ('Semen', 3);
insert into person (name, company_id) values ('Anya', 4);
insert into person (name, company_id) values ('Juliya', 4);

select p.name as имя_сотрудника, c.name as название_компании
from person as p inner join company as c
on p.company_id = c.id where c.id != 5;

select c.name as company_name, count(p.name) as number_of_employees
from person p
inner join company c
on p.company_id = c.id
group by (company_name)
order by number_of_employees desc
limit 1;