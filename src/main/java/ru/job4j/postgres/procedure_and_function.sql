create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--хранимая процедура
--удаление продуктов, у которых, стоимость, больше заданной
create or replace procedure delete_data(d_id integer)
language 'plpgsql'
as $$
    BEGIN
           if d_id > 0 THEN
             delete from products
             where id = d_id;
           else
             delete from products;
             end if;
    END
$$;

insert into products (name, producer, count, price) values ('test', 'name', 123, 44);
insert into products (name, producer, count, price) values ('name', 'producer', 10, 250);

call delete_data(1); --будет удален продукт с id = 1
call delete_data(0); --будут удалены все продукты

--хранимая функция
--выводит количество символов
create or replace function get_data(g_name text, g_producer text)
returns integer
language 'plpgsql'
as
$$
    declare
       result integer;
    begin
        if length(g_name) > 0 and length(g_producer) = 0 THEN
            select into result length(g_name)
            from products where name = g_name;
        elsif length(g_producer) > 0 and length(g_name) = 0 THEN
            select into result length(g_producer)
            from products where producer = g_producer;
        elsif length(g_producer) > 0 and length(g_name) > 0 THEN
            select into result length(g_producer) + length(g_name)
            from products where name = g_name or producer = g_producer;
        end if;
        return result;
    end;
$$;

insert into products (name, producer, count, price) values ('test', 'name', 123, 44);
insert into products (name, producer, count, price) values ('name', 'producer', 10, 250);

select get_data('','producer');
select get_data('test','');
select get_data('name','producer');


--хранимая функция
--удаление продукта по id
create or replace function del_by_id(d_id integer)
returns void
language 'plpgsql'
as
$$
    begin
        if d_id > 0 THEN
            delete from products
             where id = d_id;
         else
            delete from products;
             end if;
             end;
$$;


insert into products (name, producer, count, price) values ('test', 'name', 123, 44);
insert into products (name, producer, count, price) values ('name', 'producer', 10, 250);

select del_by_id(0);
select * from products;


