DELETE FROM books;
DELETE FROM categories;

INSERT INTO categories VALUES (1,'category1','desc','crime');
INSERT INTO books (id,book_count,description,book_image,isbn,book_name,published_time,category_id,details) VALUES (1,4,'desc1','de02-camera.jpg','1234f','sherlock','2002-08-09',1,'details');