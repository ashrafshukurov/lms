INSERT INTO categories (description, name)
VALUES ('Books about history and civilization', 'History'),
       ('Books that explore imaginary worlds', 'Fantasy');

INSERT INTO books (book_count, description, book_image, isbn, book_name, published_time, category_id)
VALUES (5, 'A fascinating book about history', 'book1.jpg', '978-1234567890', 'History Book', '2022-01-15', 1),
       (5, 'An exciting novel set in a fantasy world', 'book2.jpg', '978-9876543210', 'Fantasy Novel', '2022-03-20', 2);

INSERT INTO students (fin, address, birth_date, e_mail, first_name, last_name, password, role_type, student_group)
VALUES ('1234567890', '123 Main St', '2000-01-01', 'john@example.com', 'John', 'Doe', 'hashed_password', 'STUDENT', 'A'),
       ('9876543210', '456 Elm St', '1999-05-15', 'jane@example.com', 'Jane', 'Smith', 'hashed_password', 'STUDENT', 'B');

insert into orders (book_id, order_time, order_type, student_id)
values (1, '2023-01-01', 'BORROWED', 1),
       (1, '2023-01-02', 'RETURNED', 1),
       (1, '2023-01-03', 'BORROWED', 1),
       (1, '2023-01-01', 'RETURNED', 2),
       (1, '2023-01-02', 'BORROWED', 2),
       (1, '2023-01-03', 'RETURNED', 2);