 create table students (
     id serial primary key,
     name varchar(50)
 );

  create table authors (
     id serial primary key,
     name varchar(50)
 );

  create table books (
    id serial primary key,
     name varchar(200),
     author_id integer references authors(id)
 );

 create table orders (
     id serial primary key,
     active boolean default true,
     book_id integer references books(id),
     student_id integer references students(id)
 );

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Сергей Демидов');
insert into students (name) values ('Андрей Иванов');
insert into students (name) values ('Иван Туркович');
insert into students (name) values ('Степан Крем');

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);

insert into orders (book_id, student_id) values (3, 3);
insert into orders (book_id, student_id) values (2, 4);
insert into orders (book_id, student_id) values (2, 5);
insert into orders (book_id, student_id) values (2, 6);

create view show_books_with_author_Pushkin
     as select b.name as book, count(s.name), a.name as author from students as s
          join orders o on s.id = o.student_id
          join books b on o.book_id = b.id
          join authors a on b.author_id = a.id
          group by (b.name, a.name) having a.name = 'Александр Пушкин'
		  and length(b.name) > 10;

select * from show_books_with_author_Pushkin;
