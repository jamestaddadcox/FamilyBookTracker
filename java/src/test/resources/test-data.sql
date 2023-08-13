BEGIN TRANSACTION;

INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child) VALUES (1, 'john.smith', 'John', 'Smith', 'user1', true, 'ROLE_USER', false);
INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child) VALUES (2, 'jane.smith', 'Jane', 'Smith', 'user2', true, 'ROLE_USER', false);
INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child) VALUES (3, 'robert.johnson', 'Robert', 'Johnson', 'user3', true, 'ROLE_USER', false);

COMMIT TRANSACTION;
