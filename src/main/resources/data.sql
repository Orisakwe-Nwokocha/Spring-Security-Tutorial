TRUNCATE TABLE users CASCADE;
TRUNCATE TABLE authority CASCADE;

INSERT INTO users(id, username, password) VALUES
(100, 'username', '$2a$10$u6pp9eOs4lng/z.NmTTDIu1YthZ83qejef0fTEes9p48R6z.lXmIW'),
(101, 'admin', '$2a$10$u6pp9eOs4lng/z.NmTTDIu1YthZ83qejef0fTEes9p48R6z.lXmIW');
-- (101, 'admin', 'password');

INSERT INTO authority(id, role, user_id) VALUES
(200, 'USER', 100),
(201, 'ADMIN', 101);

