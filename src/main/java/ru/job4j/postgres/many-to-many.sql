create table users(
id serial primary key,
	username char(255)
);

create table games(
id serial primary key,
	title char(255),
	price int,
	amount_of_users int
);

create table user_have_games(
id serial primary key,
	user_id int references users(id),
	game_id int references games(id)
);

insert into users (username) values ('Andrey');
insert into users (username) values ('Ivan');
insert into users (username) values ('Alexey');

insert into games (title, price, amount_of_users) values ('BF4', 100, 2500);
insert into games (title, price, amount_of_users) values ('GTA4', 550, 19000);
insert into games (title, price, amount_of_users) values ('Minecraft', 77, 6600);

insert into user_have_games (user_id, game_id) values (1, 1);
insert into user_have_games (user_id, game_id) values (1, 2);
insert into user_have_games (user_id, game_id) values (1, 3);
insert into user_have_games (user_id, game_id) values (2, 2);
insert into user_have_games (user_id, game_id) values (2, 3);
insert into user_have_games (user_id, game_id) values (3, 1);

select *  from users;
select *  from games;
select * from user_have_games;