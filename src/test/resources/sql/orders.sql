insert into categories
values (1, 'some description', 'ROMAN');

insert into students (id, fin, password, first_name, last_name, birth_date, student_group, address)
values (1, 'fin', 'password', 'name', 'surname', current_date, 'group', 'address');

insert into books (id, book_count, description, book_image, isbn, book_name, published_time, categories_id)
values (1, 3, 'description', 'image', 'isbn', 'name', current_date, 1);

insert into orders
values (1, 1, '2023-01-01', 'RETURNED', 1);
insert into orders
values (2, 1, '2023-01-02', 'ORDERED', 1);
insert into orders
values (3, 1, '2023-01-03', 'RETURNED', 1);
insert into orders
values (4, 1, '2023-01-04', 'ORDERED', 1);

