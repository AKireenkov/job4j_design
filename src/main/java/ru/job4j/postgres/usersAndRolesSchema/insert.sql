insert into role (role) values ('Admin');
insert into role (role) values ('User');
insert into role (role) values ('Moderator');

insert into rules (rule) values ('All');
insert into rules (rule) values ('read only');
insert into rules (rule) values ('read and write');

insert into state (state) values ('to do');
insert into state (state) values ('in progress');
insert into state (state) values ('done');

insert into categories (category) values ('providing access');
insert into categories (category) values ('new user');
insert into categories (category) values ('remove user');

insert into users (name, role_id) values ('Andrey', 1);
insert into users (name, role_id) values ('Petr', 2);
insert into users (name, role_id) values ('Ivan', 3);

insert into role_and_rules (role_id, rule_id) values (1, 1);
insert into role_and_rules (role_id, rule_id) values (1, 3);
insert into role_and_rules (role_id, rule_id) values (2, 2);
insert into role_and_rules (role_id, rule_id) values (3, 3);

insert into item (item, user_id, state_id, category_id) values ('get access', 2, 1, 2);
insert into item (item, user_id, state_id, category_id) values ('remove access', 3, 2, 1);
insert into item (item, user_id, state_id, category_id) values ('password reset', 1, 3, 3);

insert into attachs (attached_type, item_id) values ('doc', 1);
insert into attachs (attached_type, item_id) values ('txt', 2);
insert into attachs (attached_type, item_id) values ('png', 3);

insert into comments (comment, item_id) values ('test comments', 3);
insert into comments (comment, item_id) values ('get for user', 1);
insert into comments (comment, item_id) values ('add new permission', 1);