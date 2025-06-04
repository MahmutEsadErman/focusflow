insert into users (email, password, first_name, last_name)
values ('test@test.de', '$2a$10$wSRnwhQhR4D1v2Qyaa2GW.LHDW2RYTNV7RclocHDp63vp2zKerfmC', 'nicolai', 'glock');

INSERT INTO roles (name, description)
VALUES ('USER', 'default');

INSERT INTO tasks (title, description, done, created_at) VALUES
('Task 1', 'Beschreibung der ersten Aufgabe', false, NOW()),
('Task 2', 'Beschreibung der zweiten Aufgabe', true, NOW()),
('Task 3', 'Noch eine Aufgabe', false, NOW());


