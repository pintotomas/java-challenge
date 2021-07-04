INSERT INTO users(id, created, updated, email, first_name, last_name) VALUES (1, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 'test@example.com', 'Tomas', 'Pinto');
INSERT INTO users(id, created, updated, email, first_name, last_name) VALUES (2, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 'roberto@diaz.com', 'Roberto', 'Diaz');

INSERT INTO loans(id, created, updated, total, user_id) VALUES (1, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 127.82, 1);
INSERT INTO loans(id, created, updated, total, user_id) VALUES (2, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 532.91, 1);
INSERT INTO loans(id, created, updated, total, user_id) VALUES (3, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 7.43, 1);
INSERT INTO loans(id, created, updated, total, user_id) VALUES (4, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 17.21, 2);
INSERT INTO loans(id, created, updated, total, user_id) VALUES (5, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 5437.12, 2);

ALTER TABLE users ALTER COLUMN id RESTART WITH 3;
ALTER TABLE loans ALTER COLUMN id RESTART WITH 6;