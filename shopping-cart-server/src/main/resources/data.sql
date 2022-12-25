INSERT INTO shopping_cart (id, customer_id, total, count_items) VALUES (1, 1, 194500, 3);
INSERT INTO shopping_cart (id, customer_id, total, count_items) VALUES (2, 2, 194500, 2);
alter sequence shopping_cart_id_seq restart with 3;

INSERT INTO shopping_cart_line_items (shoppingcart_id, sku, name, imagelink, price, quantity) VALUES (1, 325810, 'Часы-телефон JET PHONE SP1 черный', '/assets/img/products/smart_watches_and_bracelets/product_9_325810.jpg', 1499, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, sku, name, imagelink, price, quantity) VALUES (1, 392210, 'Планшет Huawei MatePad Pro 8 128Gb WiFi Grey M-Pencil', '/assets/img/products/tablets/product_8_392210.jpg', 49990, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, sku, name, imagelink, price, quantity) VALUES (1, 352960, 'Смартфон Apple iPhone 11 64GB Purple (MHDF3RU/A)', '/assets/img/products/smartphones/product_7_352960.jpg', 50399, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, sku, name, imagelink, price, quantity) VALUES (2, 325810, 'Часы-телефон JET PHONE SP1 черный', '/assets/img/products/smart_watches_and_bracelets/product_9_325810.jpg', 1499, 10);
INSERT INTO shopping_cart_line_items (shoppingcart_id, sku, name, imagelink, price, quantity) VALUES (2, 352960, 'Смартфон Apple iPhone 11 64GB Purple (MHDF3RU/A)', '/assets/img/products/smartphones/product_7_352960.jpg', 50399, 10);
