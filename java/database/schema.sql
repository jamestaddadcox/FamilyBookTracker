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
	goal int DEFAULT 0,
	goal_type varchar(10) CHECK (goal_type IN ('books', 'pages', 'time')),
	CONSTRAINT pk_prize_id PRIMARY KEY (prize_id),
	CONSTRAINT fk_family_id FOREIGN KEY (family_id) REFERENCES family(family_id)
);

CREATE TABLE prize_winner (
	prize_id int NOT NULL REFERENCES prize(prize_id),
	user_id int NOT NULL,
	CONSTRAINT pk_prize_winner PRIMARY KEY (prize_id, user_id),
	CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(user_id)
);



commit;