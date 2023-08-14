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
	pages int DEFAULT 0,
	CONSTRAINT pk_isbn PRIMARY KEY (isbn)
);

CREATE TABLE book_user (
	user_id int NOT NULL REFERENCES users(user_id),
	isbn varchar(15) NOT NULL REFERENCES book(isbn),
	minutes_read int DEFAULT 0,
	notes varchar(100),
	completed boolean DEFAULT false,
	format varchar(20) CHECK (format IN ('paper','digital','audio','readaloud-reader', 'readaloud-listener', null)),
	pages_read int DEFAULT 0,
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

INSERT INTO book (isbn, title, author, book_description) VALUES
    ('9781234567890', 'Sample Book 1', 'Author 1', 'Description 1'),
    ('9780987654321', 'Sample Book 2', 'Author 2', 'Description 2'),
    ('9789876543210', 'Sample Book 3', 'Author 3', 'Description 3'),
    ('9780123456789', 'Sample Book 4', 'Author 4', 'Description 4'),
    ('9785432109876', 'Sample Book 5', 'Author 5', 'Description 5'),
    ('9786789012340', 'Sample Book 6', 'Author 6', 'Description 6'),
    ('9787654321098', 'Sample Book 7', 'Author 7', 'Description 7'),
    ('9784567890123', 'Sample Book 8', 'Author 8', 'Description 8'),
    ('9785678901234', 'Sample Book 9', 'Author 9', 'Description 9'),
    ('9786789012345', 'Sample Book 10', 'Author 10', 'Description 10'),
    ('11hhh111hh1', 'Sample Book 10', 'Author 10', 'Description 10');

INSERT INTO book_user (user_id, isbn, minutes_read, notes, completed, pages_read) VALUES
    (1, '9781234567890', 30, 'Read aloud', false, 0),
    (1, '9780987654321', 45, 'Enjoyed it', false, 3),
    (2, '9789876543210', 60, 'Read together', false, 100),
    (2, '9780123456789', 15,'Quick read', false, 23),
    (3, '9785432109876', 90, 'Long book', false, 445),
    (3, '9786789012340', 20,'Liked the plot', false, 1),
    (4, '9787654321098', 50, 'Interesting characters', false, 324),
    (4, '9784567890123', 10, 'Didn''t finish', false, 342),
    (5, '9785678901234', 75,'Fascinating story', false, 0),
    (5, '9786789012345', 30, 'Recommended by a friend', false, 0);





commit;