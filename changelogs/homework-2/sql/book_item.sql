CREATE TABLE book_item
(
 "id"       bigserial NOT NULL,
 book_id  bigint NOT NULL,
 order_id bigint,
 price    real NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( order_id ) REFERENCES "order" ( "id" ),
 FOREIGN KEY ( book_id ) REFERENCES book ( "id" )
);
