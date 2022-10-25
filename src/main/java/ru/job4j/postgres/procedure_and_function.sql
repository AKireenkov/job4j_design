create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

--хранимая процедура
--удаление продуктов, у которых, стоимость, больше заданной
create or replace procedure delete_data(i_price integer)
language 'plpgsql'
as $$
    BEGIN
    if i_price > 0 THEN
                delete from products
                where i_price < price;
            end if;
            if i_price = 0 THEN
                delete from products;
            end if;
    END
$$;

insert into products (name, producer, count, price) values ('test', 'name', 123, 44);
insert into products (name, producer, count, price) values ('name', 'producer', 10, 250);

call delete_data(50); --будет удален второй продукт
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
        end if;
        if length(g_producer) > 0 and length(g_name) = 0 THEN
            select into result length(g_producer)
            from products where producer = g_producer;
        end if;
        if length(g_producer) > 0 and length(g_name) > 0 THEN
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


