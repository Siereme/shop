INSERT INTO category (category_id, name, path, depth) VALUES (1, 'Смартфоны и гаджеты', '1/', 1);

INSERT INTO category (category_id, parent_id, name, path, depth) VALUES (2, 1, 'Смартфоны', '1/2/', 2);
INSERT INTO category (category_id, parent_id, name, path, depth) VALUES (3, 1, 'Планшеты', '1/3/', 2);
INSERT INTO category (category_id, parent_id, name, path, depth) VALUES (4, 1, 'Умные часы и браслеты', '1/4/', 2);
INSERT INTO category (category_id, parent_id, name, path, depth) VALUES (5, 1, 'Электронные книги', '1/5/', 2);



INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (1, 412235, 'Смартфон Vivo Y53s 128Gb 6Gb глубокое море', 19599, '/assets/img/products/smartphones/product_7_412235.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (2, 359195, 'Смартфон Samsung Galaxy S21 SM-G991 256Gb 8Gb белый', 77990, '/assets/img/products/smartphones/product_7_359195.jpg');


INSERT INTO product_category (product_id, category_id) VALUES (1, 2);
INSERT INTO product_category (product_id, category_id) VALUES (2, 2);


INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (3, 392210, 'Планшет Huawei MatePad Pro 8 128 Gb WiFi Grey M-Pencil', 49990, '/assets/img/products/tablets/product_8_392210.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (4, 403373, 'Планшет Xiaomi Mi Pad 5 RU 6 128 Pearl White', 36990, '/assets/img/products/tablets/product_8_403373.jpg');

INSERT INTO product_category (product_id, category_id) VALUES (3, 3);
INSERT INTO product_category (product_id, category_id) VALUES (4, 3);


INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (5, 379476, 'Фитнес-браслет Xiaomi Mi Smart Band 6 XMSH15HM', 3099, '/assets/img/products/smart_watches_and_bracelets/product_9_379476.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (6, 325810, 'Часы-телефон JET PHONE SP1 черный', 1499, '/assets/img/products/smart_watches_and_bracelets/product_9_325810.jpg');

INSERT INTO product_category (product_id, category_id) VALUES (5, 4);
INSERT INTO product_category (product_id, category_id) VALUES (6, 4);


INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (7, 374331, 'Электронная книга PocketBook 628 Ink Ruby Red (PB628-R-CIS)', 11490, '/assets/img/products/e-books/product_10_374331.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (8, 411175, 'Электронная книга PocketBook 970 Mist Grey (PB970-M-CIS)', 19490, '/assets/img/products/e-books/product_10_411175.jpg');

INSERT INTO product_category (product_id, category_id) VALUES (7, 5);
INSERT INTO product_category (product_id, category_id) VALUES (8, 5);






INSERT INTO role (role_id, name) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, name) VALUES (2, 'USER');
INSERT INTO role (role_id, name) VALUES (3, 'ANONYMOUS');

INSERT INTO permission (permission_id, name) VALUES (1, 'site:read');
INSERT INTO permission (permission_id, name) VALUES (2, 'site:write');

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 2);

INSERT INTO shopping_cart (id, total, count_items) VALUES (1, 194500, 2);
INSERT INTO shopping_cart (id, total, count_items) VALUES (2, 214500, 2);

INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (1, 1, 1, 7);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (2, 1, 2, 11);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (3, 2, 3, 9);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (4, 2, 4, 10);

INSERT INTO user_account (id, name, surname, patronymic, email, password, phone, role_id, status, shopping_cart_id) VALUES (1, 'Admin1', 'Admin1', 'Admin1', 'admin@mail.com', '$2a$12$/GE5oRkYarA4Zsrf9l8vNOMhLxDK8B4mPI8zAaCmgoGz4R6Ptmwba','+79999999999', 1, 'ACTIVE', 1);
INSERT INTO user_account (id, name, surname, patronymic, email, password, phone, role_id, status, shopping_cart_id) VALUES (2, 'User1', 'User1', 'User1', 'user@mail.com', '$2a$12$F11pS2k4m0.9KXlOiF5W0O8QZH2jHRqNLQ7fJatlJcR5zkBJvmI7S', '+79999999998', 2, 'ACTIVE', 2);


INSERT INTO payment (id, payment_type) VALUES (1, 'При получении');


INSERT INTO order_user_details (id, name, surname, patronymic, email, phone) VALUES (1, 'User1', 'User1', 'User1', 'userdetails1@mail.com', '+79999999999');
INSERT INTO order_user_details (id, name, surname, patronymic, email, phone) VALUES (2, 'User2', 'User2', 'User2', 'userdetails2@mail.com', '+78888888888');

INSERT INTO orders (order_id, user_id, user_details_id, payment_id, total) VALUES (1, 1, 1, 1, 194500);
INSERT INTO orders (order_id, user_id, user_details_id, payment_id, total) VALUES (2, 2, 2, 1, 214500);
--INSERT INTO orders (id, payment_id, total) VALUES (2, 1, 194500);
--INSERT INTO orders (id, payment_id, total) VALUES (3, 1, 194500);
--
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 1, 2);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 3, 8);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 4, 10);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 5, 3);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 6, 4);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 7, 3);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 8, 5);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 9, 4);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 10, 1);
--
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 1, 3);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 2, 7);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 3, 2);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 4, 1);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 5, 6);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 6, 1);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 7, 3);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 8, 1);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 9, 2);
--INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 10, 4);


