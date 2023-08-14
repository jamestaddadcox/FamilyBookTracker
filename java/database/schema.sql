BEGIN TRANSACTION;

DROP TABLE IF EXISTS family, users, book, book_user, prize, prize_winner;

CREATE TABLE family (
	family_id SERIAL NOT NULL,
	family_name varchar(50) NOT NULL,
	CONSTRAINT pk_family_id PRIMARY KEY (family_id)
);

CREATE TABLE users (
	user_id SERIAL NOT NULL,
	family_id int, --NOT NULL, --disabling until we figure out how this is assigned
	username varchar(50) NOT NULL UNIQUE,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	activated boolean NOT NULL,
	role varchar(50) NOT NULL,
	is_child boolean NOT NULL,
	avatar_url varchar(100),
	CONSTRAINT pk_user PRIMARY KEY (user_id),
	CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family(family_id)
);

CREATE TABLE book (
	isbn varchar(15) NOT NULL UNIQUE,
	title varchar(50) NOT NULL,
	author varchar(50) NOT NULL,
	book_description varchar(2000),
	format varchar(10) CHECK (format IN ('paper','digital','audio','other', null)),
	CONSTRAINT pk_isbn PRIMARY KEY (isbn)
);

CREATE TABLE book_user (
	user_id int NOT NULL REFERENCES users(user_id),
	isbn varchar(15) NOT NULL REFERENCES book(isbn),
	minutes_read int NOT NULL,
	read_aloud_reader boolean NOT NULL,
	read_aloud_listen boolean NOT NULL,
	notes varchar(100),
	completed boolean NOT NULL DEFAULT false,
	pages_read int NOT NULL DEFAULT 0,
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

INSERT INTO users (family_id, username, first_name, last_name, password_hash, activated, role, is_child) VALUES
    (1, 'john.smith', 'John', 'Smith', 'password123', true, 'admin', false),
    (1, 'jane.smith', 'Jane', 'Smith', 'password123', true, 'admin', false),
    (2, 'robert.johnson', 'Robert', 'Johnson', 'password123', true, 'admin', false),
    (2, 'emily.johnson', 'Emily', 'Johnson', 'password123', true, 'admin', false),
    (3, 'michael.williams', 'Michael', 'Williams', 'password123', true, 'admin', false),
    (3, 'olivia.williams', 'Olivia', 'Williams', 'password123', true, 'admin', false),
    (4, 'david.jones', 'David', 'Jones', 'password123', true, 'admin', false),
    (4, 'sophia.jones', 'Sophia', 'Jones', 'password123', true, 'admin', false),
    (5, 'william.brown', 'William', 'Brown', 'password123', true, 'admin', false),
    (5, 'ava.brown', 'Ava', 'Brown', 'password123', true, 'admin', false);

INSERT INTO book (isbn, title, author, book_description, format) VALUES
    ('9780064400558', 'Charlottes Web', 'Somebody', 'Description 1', 'paper'),
    ('9780333791035', 'The Great Gatsby', 'F. Scott Fitzgerald', 'Description 2', 'digital'),
    ('9780582030473', 'A Tale of Two Cities', 'Charles Dickens', 'Description 3', 'audio'),
    ('978-0440413240', 'Holes', 'Louis Sachar', 'Description 4', 'other'),
    ('9785432109876', 'Sample Book 5', 'Author 5', 'Description 5', 'paper'),
    ('9786789012340', 'Sample Book 6', 'Author 6', 'Description 6', 'digital'),
    ('9787654321098', 'Sample Book 7', 'Author 7', 'Description 7', 'audio'),
    ('9784567890123', 'Sample Book 8', 'Author 8', 'Description 8', 'other'),
    ('9785678901234', 'Sample Book 9', 'Author 9', 'Description 9', 'paper'),
    ('9786789012345', 'Sample Book 10', 'Author 10', 'Description 10', 'digital'),
    ('11hhh111hh1', 'Sample Book 10', 'Author 10', 'Description 10', 'digital');

INSERT INTO book_user (user_id, isbn, minutes_read, read_aloud_reader, read_aloud_listen, notes, completed, pages_read) VALUES
    (1, '978-0064400558', 30, true, true, 'Read aloud', false, 0),
    (1, '9780333791035', 45, false, true, 'Enjoyed it', false, 3),
    (1, '9780582030473', 60, true, false, 'Read together', false, 100),
    (1, '978-0440413240', 15, true, true, 'Quick read', false, 23),
    (3, '9785432109876', 90, false, false, 'Long book', false, 445),
    (3, '9786789012340', 20, true, true, 'Liked the plot', false, 1),
    (4, '9787654321098', 50, true, false, 'Interesting characters', false, 324),
    (4, '9784567890123', 10, false, true, 'Didn''t finish', false, 342),
    (5, '9785678901234', 75, true, true, 'Fascinating story', false, 0),
    (5, '9786789012345', 30, false, false, 'Recommended by a friend', false, 0);


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