CREATE TABLE book_genre
(
 "id"       serial NOT NULL,
 genre_id int NOT NULL,
 book_id  int NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( genre_id ) REFERENCES genre ( "id" ),
 FOREIGN KEY ( book_id ) REFERENCES book ( "id" )
);

CREATE INDEX FK_11 ON book_genre
(
 genre_id
);

CREATE INDEX FK_12 ON book_genre
(
 book_id
);