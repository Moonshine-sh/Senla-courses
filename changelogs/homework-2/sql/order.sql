CREATE TABLE "order"
(
 "id"        serial NOT NULL,
 "date"      date NOT NULL,
 status_id int NOT NULL,
 person_id int NOT NULL,
 price     int NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" ),
 FOREIGN KEY ( status_id ) REFERENCES status ( "id" )
);

CREATE INDEX FK_5 ON "order"
(
 person_id
);

CREATE INDEX FK_6 ON "order"
(
 status_id
);