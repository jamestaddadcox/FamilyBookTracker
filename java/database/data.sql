BEGIN TRANSACTION;

INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child) VALUES
    (1, 'john.smith', 'John', 'Smith', 'password123', true, 'user', false),
    (1, 'jane.smith', 'Jane', 'Smith', 'password123', true, 'user', false),
    (2, 'robert.johnson', 'Robert', 'Johnson', 'password123', true, 'user', false),
    (2, 'emily.johnson', 'Emily', 'Johnson', 'password123', true, 'user', false),
    (3, 'michael.williams', 'Michael', 'Williams', 'password123', true, 'user', false),
    (3, 'olivia.williams', 'Olivia', 'Williams', 'password123', true, 'user', false),
    (4, 'david.jones', 'David', 'Jones', 'password123', true, 'user', false),
    (4, 'sophia.jones', 'Sophia', 'Jones', 'password123', true, 'user', false),
    (5, 'william.brown', 'William', 'Brown', 'password123', true, 'user', false),
    (5, 'ava.brown', 'Ava', 'Brown', 'password123', true, 'user', false);

COMMIT TRANSACTION;
