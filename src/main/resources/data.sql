INSERT INTO author (age, code, name) VALUES (12, 'A1', 'John Doe');
INSERT INTO author (age, code, name) VALUES (21, 'A2', 'Jane Doe');
INSERT INTO author (age, code, name) VALUES (32, 'A3', 'Doe Junior the Second');

INSERT INTO book (name, author) VALUES ('The great book', (SELECT id FROM author WHERE code = 'A1'));
INSERT INTO book (name, author) VALUES ('Natural History', (SELECT id FROM author WHERE code = 'A1'));
INSERT INTO book (name, author) VALUES ('FundaMETALS', (SELECT id FROM author WHERE code = 'A2'));
INSERT INTO book (name, author) VALUES ('Who Was it', (SELECT id FROM author WHERE code = 'A2'));
INSERT INTO book (name, author) VALUES ('Swear Tars', (SELECT id FROM author WHERE code = 'A3'));

INSERT INTO page (content, book) VALUES ('the first content', (SELECT id FROM book WHERE name = 'The great book'));
INSERT INTO page (content, book) VALUES ('the second content', (SELECT id FROM book WHERE name = 'The great book'));
INSERT INTO page (content, book) VALUES ('the third content', (SELECT id FROM book WHERE name = 'Natural History'));
INSERT INTO page (content, book) VALUES ('the fourth content', (SELECT id FROM book WHERE name = 'Natural History'));
INSERT INTO page (content, book) VALUES ('the fifth content', (SELECT id FROM book WHERE name = 'FundaMETALS'));
INSERT INTO page (content, book) VALUES ('the sixth content', (SELECT id FROM book WHERE name = 'FundaMETALS'));
INSERT INTO page (content, book) VALUES ('the seventh content', (SELECT id FROM book WHERE name = 'Who Was it'));
INSERT INTO page (content, book) VALUES ('the eigth content', (SELECT id FROM book WHERE name = 'Who Was it'));
INSERT INTO page (content, book) VALUES ('the nineth content', (SELECT id FROM book WHERE name = 'Nothing at All'));
INSERT INTO page (content, book) VALUES ('the tenth content', (SELECT id FROM book WHERE name = 'Nothing at All'));
