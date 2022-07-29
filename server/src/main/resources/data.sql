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



INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (1, 412235, 'Смартфон Vivo Y53s 128Gb 6Gb глубокое море', 19599, '/assets/img/products/smartphones/product_7_412235.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (2, 359195, 'Смартфон Samsung Galaxy S21 SM-G991 256Gb 8Gb белый', 77990, '/assets/img/products/smartphones/product_7_359195.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (3, 430931, 'Смартфон Samsung GALAXY A23 64GB SM-A235FZKUSKZ черный', 19490, '/assets/img/products/smartphones/product_7_430931.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (4, 433896, 'Смартфон Samsung Galaxy А33 5G 128GB SM-A336BZKGSKZ черный', 27990, '/assets/img/products/smartphones/product_7_433896.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (5, 352960, 'Смартфон Apple iPhone 11 64GB Purple (MHDF3RU/A)', 50399, '/assets/img/products/smartphones/product_7_352960.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (6, 403602, 'Смартфон Realme 8i 128Gb 4Gb черный', 17900, '/assets/img/products/smartphones/product_7_403602.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (7, 398242, 'Смартфон Apple IPHONE 13 BLUE 256GB синий (MLP73RU/A)', 91999, '/assets/img/products/smartphones/product_7_398242.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (8, 433890, 'Смартфон Samsung Galaxy A03 32Gb SM-A035FZBDSKZ синий', 9490, '/assets/img/products/smartphones/product_7_433890.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (9, 426985, 'Смартфон Xiaomi Redmi 10C 4GB 128GB Gray (38594)', 17390, '/assets/img/products/smartphones/product_7_426985.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (10, 393880, 'Смартфон Samsung Galaxy Z Flip3 SM-F711B 256Gb 8Gb зеленый', 85499, '/assets/img/products/smartphones/product_7_393880.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (11, 413605, 'Смартфон TECNO POVA 2 (4 128) Dazzle Black', 15799, '/assets/img/products/smartphones/product_7_413605.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (12, 412345, 'Смартфон Infinix Hot 11S X6812B 128Gb 6Gb зеленый', 14990, '/assets/img/products/smartphones/product_7_412345.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (13, 350369, 'Смартфон Samsung Galaxy S20 FE SM-G780F 128Gb 6Gb красный', 45990, '/assets/img/products/smartphones/product_7_350369.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (14, 433891, 'Смартфон Samsung Galaxy A03 32Gb SM-A035FZKDSKZ черный', 9490, '/assets/img/products/smartphones/product_7_433891.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (15, 426986, 'Смартфон Xiaomi Redmi 10C 4GB 64GB Blue (38596)', 15390, '/assets/img/products/smartphones/product_7_426986.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (16, 435160, 'Смартфон Huawei NOVA Y70 MEGA-L29BNX2 51096YFY', 17699, '/assets/img/products/smartphones/product_7_435160.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (17, 345049, 'Смартфон Xiaomi Redmi 9A 32Gb 2Gb зеленый', 7890, '/assets/img/products/smartphones/product_7_345049.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (18, 424989, 'Смартфон TECNO Camon 18 (6 128) Ceramic White', 15790, '/assets/img/products/smartphones/product_7_424989.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (19, 409198, 'Смартфон Apple Восстановленный iPhone 11 Pro Max 64GB Space Gray (FWHD2RU/A)', 75990, '/assets/img/products/smartphones/product_7_409198.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (20, 403601, 'Смартфон Realme 8i 128Gb 4Gb фиолетовый', 17900, '/assets/img/products/smartphones/product_7_403601.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (21, 419903, 'Смартфон Apple 12 Pro 128GB Graphite восстановленный (FGMK3RU/A)', 99999, '/assets/img/products/smartphones/product_7_419903.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (22, 345050, 'Смартфон Xiaomi Redmi 9A 32Gb 2Gb серый', 7890, '/assets/img/products/smartphones/product_7_345050.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (23, 421595, 'Смартфон Huawei nova 9 SE Midnight Black', 24990, '/assets/img/products/smartphones/product_7_421595.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (24, 412340, 'Смартфон Realme C25s 64Gb 4Gb синий', 14900, '/assets/img/products/smartphones/product_7_412340.jpg');

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
INSERT INTO product_category (product_id, category_id) VALUES (11, 7);
INSERT INTO product_category (product_id, category_id) VALUES (12, 7);
INSERT INTO product_category (product_id, category_id) VALUES (13, 7);
INSERT INTO product_category (product_id, category_id) VALUES (14, 7);
INSERT INTO product_category (product_id, category_id) VALUES (15, 7);
INSERT INTO product_category (product_id, category_id) VALUES (16, 7);
INSERT INTO product_category (product_id, category_id) VALUES (17, 7);
INSERT INTO product_category (product_id, category_id) VALUES (18, 7);
INSERT INTO product_category (product_id, category_id) VALUES (19, 7);
INSERT INTO product_category (product_id, category_id) VALUES (20, 7);
INSERT INTO product_category (product_id, category_id) VALUES (21, 7);
INSERT INTO product_category (product_id, category_id) VALUES (22, 7);
INSERT INTO product_category (product_id, category_id) VALUES (23, 7);
INSERT INTO product_category (product_id, category_id) VALUES (24, 7);



INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (25, 392210, 'Планшет Huawei MatePad Pro 8 128 Gb WiFi Grey M-Pencil', 49990, '/assets/img/products/tablets/product_8_392210.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (26, 403373, 'Планшет Xiaomi Mi Pad 5 RU 6 128 Pearl White', 36990, '/assets/img/products/tablets/product_8_403373.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (27, 390520, 'Планшет Samsung Galaxy Tab A7 Lite 8.7 32GB LTE SM-T225 (2021) серебро', 16490, '/assets/img/products/tablets/product_8_390520.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (28, 415824, 'Планшет Huawei MATEPAD T 10'' WIFI AGRK-W09 DEEPSEA BLUE', 12990, '/assets/img/products/tablets/product_8_415824.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (29, 410150, 'Планшет Huawei MATEPAD T10S 10'' LTE AGS3K-L09 DEEPSEA BLUE (53012NGU)', 19990, '/assets/img/products/tablets/product_8_410150.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (30, 392209, 'Планшет Huawei MatePad11 6 256 Gb WiFi Green', 54339, '/assets/img/products/tablets/product_8_392209.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (31, 441251, 'Планшет Apple iPad Pro 2021 WiFi 128Gb (MHQR3LL/A) серый космос', 89990, '/assets/img/products/tablets/product_8_441251.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (32, 429389, 'Планшет Teclast T40 (Pro edition)', 24490, '/assets/img/products/tablets/product_8_429389.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (33, 429390, 'Планшет Teclast M40 (Pro edition)', 21990, '/assets/img/products/tablets/product_8_429390.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (34, 421114, 'Планшет Huawei MATEPAD T 10'' WIFI AGRK-W09 DEEPSEA BLUE', 13990, '/assets/img/products/tablets/product_8_421114.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (35, 418350, 'Планшет Huawei MatePad 10.4'' 4 128 Gb WiFi stilus Grey', 28990, '/assets/img/products/tablets/product_8_418350.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (36, 410151, 'Планшет Huawei MATEPAD 10.4'' 4GB WIFI 64GB BAH3-W59 GREY (53011UDW)', 44099, '/assets/img/products/tablets/product_8_410151.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (37, 385111, 'Планшет Apple iPad Pro 12.9 2021 256Gb Wi-Fi 5 Gen (MHNJ3RU/A) серебро', 151999, '/assets/img/products/tablets/product_8_385111.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (38, 345587, 'Планшет BQ (Bright&Quick) 8068L Hornet Plus Pro black', 7990, '/assets/img/products/tablets/product_8_345587.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (39, 399518, 'Планшет Apple 10,2-inch iPad Wi-Fi Cellular 256GB серый космос 2021 (MK4E3RU/A)', 116990, '/assets/img/products/tablets/product_8_399518.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (40, 439012, 'Планшет Chuwi HiPad Plus SC9863A 8C/8Gb/128Gb 11'' серый', 23490, '/assets/img/products/tablets/product_8_439012.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (41, 385116, 'Планшет Apple iPad Pro 12.9 2021 2TB Wi-Fi 5 Gen (MHNP3RU/A) серый космос', 286999, '/assets/img/products/tablets/product_8_385116.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (42, 385110, 'Планшет Apple iPad Pro 12.9 2021 256Gb Wi-Fi 5 Gen (MHNH3RU/A) серый космос', 152999, '/assets/img/products/tablets/product_8_385110.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (43, 412985, 'Планшет Huawei MATEPAD T10S 10'' WIFI 128GB AGS3K-W09 DE.BLUE', 19990, '/assets/img/products/tablets/product_8_412985.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (44, 428777, 'Планшет Lenovo IdeaPad Duet 3 10IGL5 (82HK000VRU), серый', 40199, '/assets/img/products/tablets/product_8_428777.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (45, 414548, 'Планшет Samsung Galaxy Tab A8 SM-X205N T618 10.5'' 32Gb темно-серый', 21990, '/assets/img/products/tablets/product_8_414548.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (46, 403372, 'Планшет Xiaomi Pad 5 RU 6 128 Cosmic Gray', 36990, '/assets/img/products/tablets/product_8_403372.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (47, 414544, 'Планшет Samsung Galaxy Tab A8 SM-X200N T618 10.5''темно-серый', 22990, '/assets/img/products/tablets/product_8_414544.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (48, 392211, 'Планшет Huawei MatePad Pro12 8 256 Gb WiFi Grey', 64990, '/assets/img/products/tablets/product_8_392211.jpg');

INSERT INTO product_category (product_id, category_id) VALUES (25, 8);
INSERT INTO product_category (product_id, category_id) VALUES (26, 8);
INSERT INTO product_category (product_id, category_id) VALUES (27, 8);
INSERT INTO product_category (product_id, category_id) VALUES (28, 8);
INSERT INTO product_category (product_id, category_id) VALUES (29, 8);
INSERT INTO product_category (product_id, category_id) VALUES (30, 8);
INSERT INTO product_category (product_id, category_id) VALUES (31, 8);
INSERT INTO product_category (product_id, category_id) VALUES (32, 8);
INSERT INTO product_category (product_id, category_id) VALUES (33, 8);
INSERT INTO product_category (product_id, category_id) VALUES (34, 8);
INSERT INTO product_category (product_id, category_id) VALUES (35, 8);
INSERT INTO product_category (product_id, category_id) VALUES (36, 8);
INSERT INTO product_category (product_id, category_id) VALUES (37, 8);
INSERT INTO product_category (product_id, category_id) VALUES (38, 8);
INSERT INTO product_category (product_id, category_id) VALUES (39, 8);
INSERT INTO product_category (product_id, category_id) VALUES (40, 8);
INSERT INTO product_category (product_id, category_id) VALUES (41, 8);
INSERT INTO product_category (product_id, category_id) VALUES (42, 8);
INSERT INTO product_category (product_id, category_id) VALUES (43, 8);
INSERT INTO product_category (product_id, category_id) VALUES (44, 8);
INSERT INTO product_category (product_id, category_id) VALUES (45, 8);
INSERT INTO product_category (product_id, category_id) VALUES (46, 8);
INSERT INTO product_category (product_id, category_id) VALUES (47, 8);
INSERT INTO product_category (product_id, category_id) VALUES (48, 8);




INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (49, 379476, 'Фитнес-браслет Xiaomi Mi Smart Band 6 XMSH15HM', 3099, '/assets/img/products/smart_watches_and_bracelets/product_9_379476.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (50, 325810, 'Часы-телефон JET PHONE SP1 черный', 1499, '/assets/img/products/smart_watches_and_bracelets/product_9_325810.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (51, 325809, 'Часы-телефон JET PHONE SP1 серебристый', 1980, '/assets/img/products/smart_watches_and_bracelets/product_9_325809.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (52, 340741, 'Умные часы Huawei WATCH GT 2 Pro Night Black', 17990, '/assets/img/products/smart_watches_and_bracelets/product_9_340741.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (53, 355514, 'Умные часы Xiaomi Amazfit BIP U A2017 black', 3690, '/assets/img/products/smart_watches_and_bracelets/product_9_355514.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (54, 394438, 'Умные часы Xiaomi Amazfit T-Rex Pro A2013 grey', 12990, '/assets/img/products/smart_watches_and_bracelets/product_9_394438.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (55, 417855, 'Умные часы Realme RMA2006 (REALME WATCH 2 PRO) СЕРЕБРЯНЫЙ', 8099, '/assets/img/products/smart_watches_and_bracelets/product_9_417855.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (56, 358649, 'Смарт-часы Xiaomi Amazfit GTR 2E Slate Grey', 9990, '/assets/img/products/smart_watches_and_bracelets/product_9_358649.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (57, 430150, 'Часы Dizo DW2118 Watch 2 silver', 4990, '/assets/img/products/smart_watches_and_bracelets/product_9_430150.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (58, 435840, 'Смарт часы Xiaomi Watch POCO Watch GL (Ivory)', 5990, '/assets/img/products/smart_watches_and_bracelets/product_9_435840.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (59, 435841, 'Смарт часы Xiaomi Watch POCO Watch GL (Blue)', 5990, '/assets/img/products/smart_watches_and_bracelets/product_9_435841.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (60, 416953, 'Умные часы Xiaomi Redmi Watch 2 Lite GL (Beige) (BHR5439GL)', 4890, '/assets/img/products/smart_watches_and_bracelets/product_9_416953.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (61, 413348, 'Умные часы Huawei GT 3 JPT-B19S BLACK', 16890, '/assets/img/products/smart_watches_and_bracelets/product_9_413348.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (62, 430151, 'Часы Dizo DW2118 Watch 2 Ivory white', 4990, '/assets/img/products/smart_watches_and_bracelets/product_9_430151.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (63, 412396, 'Умные часы Xiaomi Redmi Watch 2 Lite черный', 4990, '/assets/img/products/smart_watches_and_bracelets/product_9_412396.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (64, 349765, 'Фитнес-трекер JET SPORT FT-4PRO черный', 1199, '/assets/img/products/smart_watches_and_bracelets/product_9_349765.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (65, 325808, 'Спортивные умные часы JET SPORT SW-8 черный', 3299, '/assets/img/products/smart_watches_and_bracelets/product_9_325808.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (66, 329947, 'Часы-телефон JET PHONE SP2 черный', 2990, '/assets/img/products/smart_watches_and_bracelets/product_9_329947.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (67, 325801, 'Спортивные умные часы JET SPORT SW-1 серый', 2499, '/assets/img/products/smart_watches_and_bracelets/product_9_325801.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (68, 430148, 'Часы Dizo DW2118 Watch 2 black', 3990, '/assets/img/products/smart_watches_and_bracelets/product_9_430148.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (69, 325802, 'Спортивные умные часы JET SPORT SW-2 черный', 1999, '/assets/img/products/smart_watches_and_bracelets/product_9_325802.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (70, 349766, 'Фитнес-трекер JET SPORT FT-8CH черный', 1699, '/assets/img/products/smart_watches_and_bracelets/product_9_349766.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (71, 417854, 'Умные часы Realme RMA2006 (REALME WATCH 2 PRO) МАТОВЫЙ СЕРЫЙ', 5999, '/assets/img/products/smart_watches_and_bracelets/product_9_417854.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (72, 410979, 'Смарт-часы Haylou LS04 (RS3) черный', 5499, '/assets/img/products/smart_watches_and_bracelets/product_9_410979.jpg');

INSERT INTO product_category (product_id, category_id) VALUES (49, 9);
INSERT INTO product_category (product_id, category_id) VALUES (50, 9);
INSERT INTO product_category (product_id, category_id) VALUES (51, 9);
INSERT INTO product_category (product_id, category_id) VALUES (52, 9);
INSERT INTO product_category (product_id, category_id) VALUES (53, 9);
INSERT INTO product_category (product_id, category_id) VALUES (54, 9);
INSERT INTO product_category (product_id, category_id) VALUES (55, 9);
INSERT INTO product_category (product_id, category_id) VALUES (56, 9);
INSERT INTO product_category (product_id, category_id) VALUES (57, 9);
INSERT INTO product_category (product_id, category_id) VALUES (58, 9);
INSERT INTO product_category (product_id, category_id) VALUES (59, 9);
INSERT INTO product_category (product_id, category_id) VALUES (60, 9);
INSERT INTO product_category (product_id, category_id) VALUES (61, 9);
INSERT INTO product_category (product_id, category_id) VALUES (62, 9);
INSERT INTO product_category (product_id, category_id) VALUES (63, 9);
INSERT INTO product_category (product_id, category_id) VALUES (64, 9);
INSERT INTO product_category (product_id, category_id) VALUES (65, 9);
INSERT INTO product_category (product_id, category_id) VALUES (66, 9);
INSERT INTO product_category (product_id, category_id) VALUES (67, 9);
INSERT INTO product_category (product_id, category_id) VALUES (68, 9);
INSERT INTO product_category (product_id, category_id) VALUES (69, 9);
INSERT INTO product_category (product_id, category_id) VALUES (70, 9);
INSERT INTO product_category (product_id, category_id) VALUES (71, 9);
INSERT INTO product_category (product_id, category_id) VALUES (72, 9);



INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (73, 374331, 'Электронная книга PocketBook 628 Ink Ruby Red (PB628-R-CIS)', 11490, '/assets/img/products/e-books/product_10_374331.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (74, 411175, 'Электронная книга PocketBook 970 Mist Grey (PB970-M-CIS)', 19490, '/assets/img/products/e-books/product_10_411175.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (75, 433321, 'Электронная книга Ritmix RBK-617 Black', 5490, '/assets/img/products/e-books/product_10_433321.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (76, 374333, 'Электронная книга PocketBook InkPad X Metallic Grey (PB1040-J-CIS)', 34990, '/assets/img/products/e-books/product_10_374333.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (77, 374330, 'Электронная книга PocketBook 628 Ink Black (PB628-P-CIS)', 11990, '/assets/img/products/e-books/product_10_374330.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (78, 324928, 'Электронная книга PocketBook 740 InkPad 3 Pro Metallic Grey', 20990, '/assets/img/products/e-books/product_10_324928.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (79, 433323, 'Электронная книга Ritmix RBK-678FL black', 6990, '/assets/img/products/e-books/product_10_433323.jpg');
INSERT INTO product (product_id, article_id, name, price, image_link) VALUES (80, 433322, 'Электронная книга Ritmix RBK-677FL black', 6490, '/assets/img/products/e-books/product_10_433322.jpg');

INSERT INTO product_category (product_id, category_id) VALUES (73, 10);
INSERT INTO product_category (product_id, category_id) VALUES (74, 10);
INSERT INTO product_category (product_id, category_id) VALUES (75, 10);
INSERT INTO product_category (product_id, category_id) VALUES (76, 10);
INSERT INTO product_category (product_id, category_id) VALUES (77, 10);
INSERT INTO product_category (product_id, category_id) VALUES (78, 10);
INSERT INTO product_category (product_id, category_id) VALUES (79, 10);
INSERT INTO product_category (product_id, category_id) VALUES (80, 10);






INSERT INTO role (role_id, name) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, name) VALUES (2, 'USER');

INSERT INTO permission (permission_id, name) VALUES (1, 'site:read');
INSERT INTO permission (permission_id, name) VALUES (2, 'site:write');

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);

INSERT INTO user (id, name, surname, patronymic, email, password, phone, role_id, status) VALUES (1, 'Admin1', 'Admin1', 'Admin1', 'admin@mail.com', '$2a$12$/GE5oRkYarA4Zsrf9l8vNOMhLxDK8B4mPI8zAaCmgoGz4R6Ptmwba','+7-999-999-99-99', 1, 'ACTIVE');
INSERT INTO user (id, name, surname, patronymic, email, password, phone, role_id, status) VALUES (2, 'User1', 'User1', 'User1', 'user@mail.com', '$2a$12$F11pS2k4m0.9KXlOiF5W0O8QZH2jHRqNLQ7fJatlJcR5zkBJvmI7S', '+7-999-999-99-99', 2, 'ACTIVE');


INSERT INTO shopping_cart (id, user_id) VALUES (1, 1);
INSERT INTO shopping_cart (id, user_id) VALUES (2, 2);

INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (1, 2, 1, 1);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (2, 2, 2, 1);
INSERT INTO shopping_cart_items (id, shopping_cart_id, cart_product_id, count) VALUES (3, 2, 3, 1);


INSERT INTO payment (id, payment_type) VALUES (1, 'При получении');

INSERT INTO orders (id, user_id, payment_id, total) VALUES (1, 1, 1, 194500);
INSERT INTO orders (id, user_id, payment_id, total) VALUES (2, 1, 1, 194500);
INSERT INTO orders (id, user_id, payment_id, total) VALUES (3, 2, 1, 194500);

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
