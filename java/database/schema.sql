BEGIN TRANSACTION;

DROP TABLE IF EXISTS family, users, book, book_user, prize, prize_winner;

CREATE TABLE family (
	family_id SERIAL NOT NULL,
	family_name varchar(50) NOT NULL,
	CONSTRAINT pk_family_id PRIMARY KEY (family_id)
);

CREATE TABLE users (
	user_id SERIAL NOT NULL,
	family_id int NOT NULL,
	username varchar(50) NOT NULL UNIQUE,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	activated boolean NOT NULL,
	role varchar(50) NOT NULL,
	is_child boolean NOT NULL,
	avatar_url varchar(200) NOT NULL,
	CONSTRAINT pk_user PRIMARY KEY (user_id),
	CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family(family_id)
);

CREATE TABLE book (
	isbn varchar(15) NOT NULL UNIQUE,
	title varchar(50) NOT NULL,
	author varchar(50) NOT NULL,
	book_description varchar(2000) NOT NULL,
	format varchar(10) CHECK (format IN ('paper','digital','audio','other')),
	CONSTRAINT pk_isbn PRIMARY KEY (isbn)
);

CREATE TABLE book_user (
	user_id int NOT NULL REFERENCES users(user_id),
	isbn varchar(15) NOT NULL UNIQUE,
	minutes_read int NOT NULL,
	read_aloud_reader boolean NOT NULL,
	read_aloud_listen boolean NOT NULL,
	notes varchar(100),
	completed boolean NOT NULL DEFAULT false,
	CONSTRAINT pk_book_user_table PRIMARY KEY (user_id, isbn),
	CONSTRAINT fk_isbn FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE prize (
	prize_id SERIAL NOT NULL,
	family_id int NOT NULL,
	prize_name varchar(50) NOT NULL,
	prize_description varchar(100) NOT NULL,
	milestone boolean NOT NULL,
	start_date date NOT NULL,
	user_group varchar(10) CHECK (user_group IN ('adult','child', 'both')),
	end_date date NOT NULL,
	CONSTRAINT pk_prize_id PRIMARY KEY (prize_id),
	CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family(family_id)
);

CREATE TABLE prize_winner (
	prize_id int NOT NULL REFERENCES prize(prize_id),
	user_id int NOT NULL,
	CONSTRAINT pk_prize_winner PRIMARY KEY (prize_id, user_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);

INSERT INTO family (family_name) VALUES
    ('Smith Family'),
    ('Johnson Family'),
    ('Williams Family'),
    ('Jones Family'),
    ('Brown Family'),
    ('Davis Family'),
    ('Miller Family'),
    ('Wilson Family'),
    ('Moore Family'),
    ('Taylor Family');
INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child, avatar_url) VALUES
    (1, 'john.smith', 'John', 'Smith', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (1, 'jane.smith', 'Jane', 'Smith', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (2, 'robert.johnson', 'Robert', 'Johnson', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (2, 'emily.johnson', 'Emily', 'Johnson', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (3, 'michael.williams', 'Michael', 'Williams', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (3, 'olivia.williams', 'Olivia', 'Williams', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (4, 'david.jones', 'David', 'Jones', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (4, 'sophia.jones', 'Sophia', 'Jones', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (5, 'william.brown', 'William', 'Brown', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx'),
    (5, 'ava.brown', 'Ava', 'Brown', 'password123', true, 'user', false, 'https://api.multiavatar.com/Binx');

INSERT INTO book (isbn, title, author, book_description, format) VALUES
    ('9781234567890', 'Sample Book 1', 'Author 1', 'Description 1', 'paper'),
    ('9780987654321', 'Sample Book 2', 'Author 2', 'Description 2', 'digital'),
    ('9789876543210', 'Sample Book 3', 'Author 3', 'Description 3', 'audio'),
    ('9780123456789', 'Sample Book 4', 'Author 4', 'Description 4', 'other'),
    ('9785432109876', 'Sample Book 5', 'Author 5', 'Description 5', 'paper'),
    ('9786789012340', 'Sample Book 6', 'Author 6', 'Description 6', 'digital'),
    ('9787654321098', 'Sample Book 7', 'Author 7', 'Description 7', 'audio'),
    ('9784567890123', 'Sample Book 8', 'Author 8', 'Description 8', 'other'),
    ('9785678901234', 'Sample Book 9', 'Author 9', 'Description 9', 'paper'),
    ('9786789012345', 'Sample Book 10', 'Author 10', 'Description 10', 'digital');

INSERT INTO book_user (user_id, isbn, minutes_read, read_aloud_reader, read_aloud_listen, notes, completed) VALUES
    (1, '9781234567890', 30, true, true, 'Read aloud'),
    (1, '9780987654321', 45, false, true, 'Enjoyed it'),
    (2, '9789876543210', 60, true, false, 'Read together'),
    (2, '9780123456789', 15, true, true, 'Quick read'),
    (3, '9785432109876', 90, false, false, 'Long book'),
    (3, '9786789012340', 20, true, true, 'Liked the plot'),
    (4, '9787654321098', 50, true, false, 'Interesting characters'),
    (4, '9784567890123', 10, false, true, 'Didn''t finish'),
    (5, '9785678901234', 75, true, true, 'Fascinating story'),
    (5, '9786789012345', 30, false, false, 'Recommended by a friend');

INSERT INTO prize (family_id, prize_name, prize_description, milestone, start_date, user_group, end_date) VALUES
    (1, 'Prize 1', 'Description for Prize 1', false, '2023-08-01', 'adult', '2023-08-31'),
    (1, 'Prize 2', 'Description for Prize 2', true, '2023-08-01', 'child', '2023-08-31'),
    (2, 'Prize 3', 'Description for Prize 3', false, '2023-08-01', 'both', '2023-08-31'),
    (2, 'Prize 4', 'Description for Prize 4', true, '2023-08-01', 'adult', '2023-08-31'),
    (3, 'Prize 5', 'Description for Prize 5', false, '2023-08-01', 'child', '2023-08-31'),
    (3, 'Prize 6', 'Description for Prize 6', true, '2023-08-01', 'both', '2023-08-31'),
    (4, 'Prize 7', 'Description for Prize 7', false, '2023-08-01', 'adult', '2023-08-31'),
    (4, 'Prize 8', 'Description for Prize 8', true, '2023-08-01', 'child', '2023-08-31'),
    (5, 'Prize 9', 'Description for Prize 9', false, '2023-08-01', 'both', '2023-08-31');

commit;

