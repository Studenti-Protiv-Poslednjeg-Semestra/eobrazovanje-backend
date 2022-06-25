# TODO: Refactor needed and split to files (or not?)

# Users stuff
INSERT INTO authority (name) VALUES
    ('ROLE_ADMIN'),
    ('ROLE_TEACHER'),
    ('ROLE_STUDENT');

INSERT INTO user (ucn, email, first_name, last_name, password) VALUES
    ('123', 'a@a.a', 'a', 'a', '$2a$10$nbkxWMuU1PIvXuyE14dE4O/Fq6dl77vuggD1TLBSeU7hLa7l4tABu');

INSERT INTO user_authority (user_id, authority_id) VALUES
    ((SELECT id FROM user WHERE email = 'a@a.a'), (SELECT id FROM authority WHERE name LIKE '%ADMIN'));

# Other stuff
INSERT INTO major (duration, name) VALUES
    (8, 'SoFtVeRsKe I iNfOrMaCiOnE tEhNoLoGiJe');

INSERT INTO syllabus (major_id) VALUES
    ((SELECT id FROM major WHERE name = 'SoFtVeRsKe I iNfOrMaCiOnE tEhNoLoGiJe'));
