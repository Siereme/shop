INSERT INTO payment (id, payment_type, description) VALUES (1, 'Наличными', 'При получении');
INSERT INTO payment (id, payment_type, description) VALUES (2, 'Картой', 'Оплата онлайн');
alter sequence payment_id_seq restart with 3;

INSERT INTO receipt (id, receipt_type) VALUES (1, 'Самовывоз');
INSERT INTO receipt (id, receipt_type) VALUES (2, 'Доставка');
alter sequence payment_id_seq restart with 3;

INSERT INTO order_status (id, status_type, description) VALUES (1, 'Ожидает оплаты', 'Заказ создан и ожидает оплаты');
INSERT INTO order_status (id, status_type, description) VALUES (2, 'Принят в обработку', 'Заказ оплачен и принят в обработку');
INSERT INTO order_status (id, status_type, description) VALUES (3, 'Отправлен', 'Заказ отправлен в место назначения');
INSERT INTO order_status (id, status_type, description) VALUES (4, 'Доставлен', 'Заказ прибыл в место назначения');
INSERT INTO order_status (id, status_type, description) VALUES (5, 'Получен', 'Заказ получен');
alter sequence payment_id_seq restart with 6;

INSERT INTO shop_address (id, country, city, street, building, flat) VALUES (1, 'Россия', 'Москва', 'ул. Вешняковская', '22А', '');
INSERT INTO shop_address (id, country, city, street, building, flat) VALUES (2, 'Россия', 'Москва', 'Комсомольский пр.', '28', '');
INSERT INTO shop_address (id, country, city, street, building, flat) VALUES (3, 'Россия', 'Москва', 'Манежная пл.', '1', '');
INSERT INTO shop_address (id, country, city, street, building, flat) VALUES (4, 'Россия', 'Москва', 'Большая Бронная ул.', '29', '8');
INSERT INTO shop_address (id, country, city, street, building, flat) VALUES (5, 'Россия', 'Москва', 'пл. Тверская Застава', '2', '8');
alter sequence shop_address_id_seq restart with 6;

INSERT INTO orders (id, customer_id, country, city, street, building, flat, name, surname, patronymic, email, phone, receipt_id, payment_id, status_id, total) VALUES (1, 1, 'Россия', 'Москва', 'ул. Вешняковская', '22А', '', 'User1', 'User1', 'User1', 'userdetails1@mail.com', '+79999999999', 1, 1, 1, 194500);
INSERT INTO orders (id, customer_id, country, city, street, building, flat, name, surname, patronymic, email, phone, receipt_id, payment_id, status_id, total) VALUES (2, 1, 'Россия', 'Москва', 'ул. Ленина', '22А', '', 'User2', 'User2', 'User2', 'userdetails2@mail.com', '+79999999998', 2, 2, 2, 194500);
INSERT INTO orders (id, customer_id, country, city, street, building, flat, name, surname, patronymic, email, phone, receipt_id, payment_id, status_id, total) VALUES (3, 2, 'Россия', 'Москва', 'ул. Пушкина', '22А', '', 'User1', 'User1', 'User1', 'userdetails1@mail.com', '+79999999997', 1, 2, 3, 194500);
alter sequence orders_id_seq restart with 4;

INSERT INTO order_line_items (order_id, article, name, price, imagelink, quantity) VALUES (1, 1123, 'product1', 1000, '', 10);
INSERT INTO order_line_items (order_id, article, name, price, imagelink, quantity) VALUES (1, 34324, 'product2', 1000, '', 10);
INSERT INTO order_line_items (order_id, article, name, price, imagelink, quantity) VALUES (2, 1123, 'product1', 1000, '', 10);
INSERT INTO order_line_items (order_id, article, name, price, imagelink, quantity) VALUES (2, 34324, 'product2', 1000, '', 100);


