BEGIN TRANSACTION;

INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child, avatar_url) VALUES (1, 'john.smith', 'John', 'Smith', 'user1', true, 'ROLE_ADMIN', false, "");
INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child, avatar_url) VALUES (2, 'jane.smith', 'Jane', 'Smith', 'user2', true, 'ROLE_ADMIN', false, "");
INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child, avatar_url) VALUES (3, 'cam.smith', 'Cam', 'Smith', 'user3', true, 'ROLE_ADMIN', false, "");

INSERT INTO book (isbn, title, author, book_description, format) VALUES ('9781234567890', 'Sample Book 1', 'Author 1', 'Description 1', 'paper');
INSERT INTO book (isbn, title, author, book_description, format) VALUES ('9780987654321', 'Sample Book 2', 'Author 2', 'Description 2', 'digital');
INSERT INTO book (isbn, title, author, book_description, format) VALUES ('9785432109876', 'Sample Book 3', 'Author 3', 'Description 3', 'audio');

INSERT INTO book_user (user_id, isbn, minutes_read, read_aloud_reader, read_aloud_listen, notes, completed, pages_read) VALUES (1, '9781234567890', 30, true, true, 'Read aloud', false, 10);
INSERT INTO book_user (user_id, isbn, minutes_read, read_aloud_reader, read_aloud_listen, notes, completed, pages_read) VALUES (2, '9780987654321', 40, true, true, 'Read aloud', false, 50);
INSERT INTO book_user (user_id, isbn, minutes_read, read_aloud_reader, read_aloud_listen, notes, completed, pages_read) VALUES (3, '9785432109876', 50, true, true, 'Read aloud', false, 100);

INSERT INTO family (family_name) VALUES ('Smith Family');
INSERT INTO family (family_name) VALUES ('Johnson Family');

COMMIT TRANSACTION;
