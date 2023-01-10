CREATE TABLE book_author
(
 "id"        serial NOT NULL,
 author_id int NOT NULL,
 book_id   int NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( author_id ) REFERENCES author ( "id" ),
 FOREIGN KEY ( book_id ) REFERENCES book ( "id" )
);

CREATE INDEX FK_9 ON book_author
(
 author_id
);

CREATE INDEX FK_10 ON book_author
(
 book_id
);