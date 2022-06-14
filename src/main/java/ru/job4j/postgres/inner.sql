create table role (
    id serial primary key,
    role text
);

create table users (
    id serial primary key,
    name text,
    role_id int references role(id)
);

insert into role (role) values ('Admin');
insert into role (role) values ('User');
insert into role (role) values ('Moderator');

insert into users (name, role_id) values ('Andrey', 1);
insert into users (name, role_id) values ('Petr', 2);
insert into users (name, role_id) values ('Ivan', 3);

select u.name, r.role
from users as u
join role as r
on u.role_id = r.id;

select u.name as Имя, r.role as Роль
from users as u
join role as r
on u.role_id = r.id;

select u.id as id_пользователя, r.role as Роль
from users as u
join role as r
on u.role_id = r.id;