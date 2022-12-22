INSERT INTO shopping_cart (id, customer_id, total, count_items) VALUES (1, 1, 194500, 3);
INSERT INTO shopping_cart (id, customer_id, total, count_items) VALUES (2, 2, 194500, 2);
alter sequence shopping_cart_id_seq restart with 3;

INSERT INTO shopping_cart_line_items (shoppingcart_id, article, name, imagelink, price, quantity) VALUES (1, 2121, 'product1', '', 1000, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, article, name, imagelink, price, quantity) VALUES (1, 2122, 'product2', '', 1000, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, article, name, imagelink, price, quantity) VALUES (1, 2123, 'product3', '', 1000, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, article, name, imagelink, price, quantity) VALUES (2, 2121, 'product1', '', 1000, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, article, name, imagelink, price, quantity) VALUES (2, 2122, 'product2', '', 1000, 10);
