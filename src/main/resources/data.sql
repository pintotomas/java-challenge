INSERT INTO users(id, created, updated, email, first_name, last_name) VALUES (1, '2021-07-03 13:47:52.654432', '2021-07-03 13:47:52.654432', 'test@example.com', 'Tomas', 'Pinto');

ALTER TABLE users ALTER COLUMN id RESTART WITH 2;