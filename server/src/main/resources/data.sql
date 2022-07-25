INSERT INTO category (category_id, name, lineage, depth) VALUES (1, 'Смартфоны и гаджеты', 1, 1);
INSERT INTO category (category_id, name, lineage, depth) VALUES (2, 'Телевизоры', 2, 1);
INSERT INTO category (category_id, name, lineage, depth) VALUES (3, 'Компьютерная техника', 3, 1);
INSERT INTO category (category_id, name, lineage, depth) VALUES (4, 'Умный дом', 4, 1);
INSERT INTO category (category_id, name, lineage, depth) VALUES (5, 'Аудио-видео', 5, 1);
INSERT INTO category (category_id, name, lineage, depth) VALUES (6, 'Техника для дома', 6, 1);

INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (7, 1, 'Смартфоны', 1, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (8, 1, 'Планшеты', 1, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (9, 1, 'Умные часы и браслеты', 1, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (10, 1, 'Электронные книги', 1, 2);

INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (11, 3, 'Блоки питания', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (12, 3, 'Видеокарты', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (13, 3, 'Жесткие диски и SSD накопители', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (14, 3, 'Материнские платы', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (15, 3, 'Оперативная память', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (16, 3, 'Процессоры', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (17, 3, 'Ноутбуки', 3, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (18, 3, 'Моноблоки', 3, 2);

INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (19, 5, 'Аудиотехника', 5, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (20, 19, 'Домашние кинотеатры', 5, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (21, 19, 'Музыкальные центры', 5, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (22, 19, 'Магнитолы', 5, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (23, 5, 'Проекторы', 5, 2);

INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (24, 6, 'Системы безопасноcти и видеонаблюдения', 6, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (25, 24, 'Видеонаблюдение', 6, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (26, 24, 'Видеодомофоны', 6, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (27, 6, 'Умный дом', 6, 2);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (28, 27, 'Блоки управления', 6, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (29, 27, 'Датчики', 6, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (30, 27, 'Камеры', 6, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (31, 27, 'Комплекты умный дом', 6, 3);
INSERT INTO category (category_id, parent_id, name, lineage, depth) VALUES (32, 27, 'Лампы, светодиодные ленты', 6, 3);



INSERT INTO product (product_id, name, price) VALUES (1, 'Смартфон Apple IPHONE 13 BLUE 256GB синий (MLP73RU/A)', 97799);
INSERT INTO product (product_id, name, price) VALUES (2, 'Смартфон Apple Восстановленный IPHONE XS 512GB золотой RUS (FT9N2RU/A)', 59990);
INSERT INTO product (product_id, name, price) VALUES (3, 'Смартфон Realme 8i 128Gb 4Gb черный', 18490);
INSERT INTO product (product_id, name, price) VALUES (4, 'Смартфон Apple iPhone 12 64GB Black (MGJ53RU/A)', 64399);
INSERT INTO product (product_id, name, price) VALUES (5, 'Смартфон Samsung Galaxy M52 5G SM-M526 128Gb 6Gb черный', 32990);
INSERT INTO product (product_id, name, price) VALUES (6, 'Смартфон Realme 8i 128Gb 4Gb фиолетовый', 18490);
INSERT INTO product (product_id, name, price) VALUES (7, 'Смартфон Samsung Galaxy A52 SM-A525F 256Gb голубой', 39990);
INSERT INTO product (product_id, name, price) VALUES (8, 'Смартфон Huawei NOVA 8 blush', 32990);
INSERT INTO product (product_id, name, price) VALUES (9, 'Смартфон Xiaomi Redmi note 10S RU 6 128 Pebble White', 19990);
INSERT INTO product (product_id, name, price) VALUES (10, 'Смартфон Infinix Zero X pro X6811 128Gb 8Gb черный', 29990);

INSERT INTO product_category (product_id, category_id) VALUES (1, 7);
INSERT INTO product_category (product_id, category_id) VALUES (2, 7);
INSERT INTO product_category (product_id, category_id) VALUES (3, 7);
INSERT INTO product_category (product_id, category_id) VALUES (4, 7);
INSERT INTO product_category (product_id, category_id) VALUES (5, 7);
INSERT INTO product_category (product_id, category_id) VALUES (6, 7);
INSERT INTO product_category (product_id, category_id) VALUES (7, 7);
INSERT INTO product_category (product_id, category_id) VALUES (8, 7);
INSERT INTO product_category (product_id, category_id) VALUES (9, 7);
INSERT INTO product_category (product_id, category_id) VALUES (10, 7);






INSERT INTO role (role_id, name) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, name) VALUES (2, 'USER');

INSERT INTO permission (permission_id, name) VALUES (1, 'site:read');
INSERT INTO permission (permission_id, name) VALUES (2, 'site:write');

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);

INSERT INTO user (id, name, surname, patronymic, email, password, phone, role_id, status) VALUES (1, 'Admin1', 'Admin1', 'Admin1', 'admin@mail.com', '$2a$12$IPyJV7Pv6Z5DfX5J6G.isOdLhE5EfONS0SIejqPZtvsDnQ6rcbFnC','+7-999-999-99-99', 1, 'ACTIVE');
INSERT INTO user (id, name, surname, patronymic, email, password, phone, role_id, status) VALUES (2, 'User1', 'User1', 'User1', 'user@mail.com', '$2a$12$TDwqybb/Fi0FZM4quZ54ne3MHkj8D66g.YEXa66jqeiUmkPDtVsBe', '+7-999-999-99-99', 2, 'ACTIVE');


INSERT INTO shopping_cart (id, user_id) VALUES (1, 1);
INSERT INTO shopping_cart (id, user_id) VALUES (2, 2);

INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (1, 2, 1, 1);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (2, 2, 2, 1);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (3, 2, 3, 1);


INSERT INTO payment (id, payment_type) VALUES (1, 'При получении');

INSERT INTO orders (id, user_id, payment_id) VALUES (1, 1, 1);
INSERT INTO orders (id, user_id, payment_id) VALUES (2, 2, 1);

INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 1, 2);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 3, 8);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 4, 10);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 5, 3);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 6, 4);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 7, 3);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 8, 5);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 9, 4);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (1, 10, 1);

INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 1, 3);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 2, 7);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 3, 2);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 4, 1);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 5, 6);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 6, 1);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 7, 3);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 8, 1);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 9, 2);
INSERT INTO order_product_items (order_id, order_product_id, count) VALUES (2, 10, 4);
