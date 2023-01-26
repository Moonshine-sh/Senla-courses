CREATE TABLE book_genre
(
 genre_id bigint NOT NULL,
 book_id  bigint NOT NULL,
 PRIMARY KEY ( genre_id,book_id ),
 FOREIGN KEY ( genre_id ) REFERENCES genre ( "id" ),
 FOREIGN KEY ( book_id ) REFERENCES book ( "id" )
);
