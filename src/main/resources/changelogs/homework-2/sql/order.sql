CREATE TABLE "order"
(
 "id"        bigserial NOT NULL,
 "date"      timestamp NOT NULL,
 status_id bigint NOT NULL,
 person_id bigint NOT NULL,
 price     int NOT NULL,
 PRIMARY KEY ( "id" ),
 FOREIGN KEY ( person_id ) REFERENCES person ( "id" ),
 FOREIGN KEY ( status_id ) REFERENCES status ( "id" )
);
