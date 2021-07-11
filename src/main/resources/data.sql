INSERT INTO user(id, username) VALUES (1, 'user1');
INSERT INTO user(id, username) VALUES (2, 'user2');
INSERT INTO user(id, username) VALUES (3, 'user3');

COMMIT;

INSERT INTO customer(id, name, surname) VALUES (1, 'Rick', 'Sanchez');
INSERT INTO customer(id, name, surname) VALUES (2, 'Mortimer', 'Smith');
INSERT INTO customer(id, name, surname) VALUES (3, 'Beth', 'Smith');

COMMIT;