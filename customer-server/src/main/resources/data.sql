INSERT INTO role (role_id, name) VALUES (1, 'ADMIN');
INSERT INTO role (role_id, name) VALUES (2, 'USER');
INSERT INTO role (role_id, name) VALUES (3, 'ANONYMOUS');
alter sequence role_role_id_seq restart with 4;

INSERT INTO permission (permission_id, name) VALUES (1, 'site:read');
INSERT INTO permission (permission_id, name) VALUES (2, 'site:write');
alter sequence permission_permission_id_seq restart with 3;

INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 2);

INSERT INTO customer (id, name, surname, patronymic, email, password, phone, role_id, status) VALUES (1, 'Admin1', 'Admin1', 'Admin1', 'admin@mail.com', '$2a$12$/GE5oRkYarA4Zsrf9l8vNOMhLxDK8B4mPI8zAaCmgoGz4R6Ptmwba','7 (999) 999-9999', 1, 'ACTIVE');
INSERT INTO customer (id, name, surname, patronymic, email, password, phone, role_id, status) VALUES (2, 'User1', 'User1', 'User1', 'user@mail.com', '$2a$12$F11pS2k4m0.9KXlOiF5W0O8QZH2jHRqNLQ7fJatlJcR5zkBJvmI7S', '7 (999) 999-9998', 2, 'ACTIVE');
alter sequence customer_id_seq restart with 3;
