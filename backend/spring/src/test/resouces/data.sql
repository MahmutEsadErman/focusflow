INSERT INTO users (email, password, first_name, last_name)
VALUES ('test@example.com',
        '$2a$10$Dow1DLrgZcXr1V2wHuV2zOZtkS1gjbwz6GbQPGGBcZdRJAYJ0Cjle', -- "Password123!"
        'Max',
        'Mustermann');

insert into roles (name, description)
values ('USER', 'default');
