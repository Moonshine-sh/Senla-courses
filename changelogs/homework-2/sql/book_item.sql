CREATE TABLE book_item
(
 "id"       serial NOT NULL,
 book_id  int NOT NULL,
 order_id int,
 price    real NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( order_id ) REFERENCES "order" ( "id" ),
 FOREIGN KEY ( book_id ) REFERENCES book ( "id" )
);

CREATE INDEX FK_7 ON book_item
(
 order_id
);

CREATE INDEX FK_8 ON book_item
(
 book_id
);