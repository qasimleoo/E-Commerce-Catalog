INSERT INTO users (id, email, password, first_name, last_name)
VALUES (1, 'admin@gmail.com', '$2a$12$PFJBGfJ2WUFkIYDF8iQSFeT3r4WyJAJxMlPGUhp.xIOLetia/6x9G', 'Admin', 'Qasim')
ON CONFLICT (id) DO NOTHING;