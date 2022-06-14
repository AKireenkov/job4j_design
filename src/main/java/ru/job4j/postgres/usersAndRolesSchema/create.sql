create table users (
    id serial primary key,
    name text,
    role_id int references role(id)
);

create table role (
    id serial primary key,
    role text
);

create table rules (
    id serial primary key,
    rule text,

);

create table role_and_rules (
    id serial primary key,
    role_id int references role(id),
    rule_id int references rules(id)
);

create table item (
    id serial primary key,
    item text,
    state_id int references state(id),
    category_id int references categories(id)
);

create table items_for_users (
    id serial primary key,
    user_id int references users(id),
    item_id int references item(id)
);

create table comments (
    id serial primary key,
    comment text,
    item_id int references item(id)
);

create table attachs (
    id serial primary key,
    attached_type text,
    item_id int references item(id)
);

create table state (
    id serial primary key,
    state text
);

create table categories (
    id serial primary key,
    category text
);